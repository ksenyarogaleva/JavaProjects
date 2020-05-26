package com.ks_rogaleva.hospital;

import com.ks_rogaleva.hospital.doctor.Doctor;
import com.ks_rogaleva.hospital.exceptions.CardAlreadyExistsException;
import com.ks_rogaleva.hospital.patient.Patient;
import com.ks_rogaleva.hospital.patient.PatientCard;
import com.ks_rogaleva.hospital.registerOffice.RegisterOffice;
import com.ks_rogaleva.hospital.treatment.Medicine;
import com.ks_rogaleva.hospital.treatment.Operation;
import com.ks_rogaleva.hospital.treatment.Procedure;
import com.ks_rogaleva.hospital.treatment.Treatment;

import java.io.*;
import java.time.DayOfWeek;
import java.util.*;

public class Main {
    private static String filePath="exportCardFile.txt";
    private static List<PatientCard> cards=new ArrayList<>();
    private static List<Patient> patients=new ArrayList<>();
    private static List<Doctor> doctors=new ArrayList<>();
    private static List<Medicine> medicines=new ArrayList<>();
    private static List<Procedure> procedures=new ArrayList<>();
    private static List<Operation> operations=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        boolean isRunning=true;

        while(isRunning) {
            System.out.println("Выберите действие: ");
            System.out.println("\t1-добавить пациентов\n\t2-добавить докторов\n\t3-добавить лекарста\n" +
                    "\t4-добавить процедуры\n\t5-добавить операции\n\t6-создать карты пациентов\n\t0-ничего");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    createPatients();
                    break;
                case 2:
                    createDoctors();
                    break;
                case 3:
                    createMedicines();
                    break;
                case 4:
                    createProcedures();
                    break;
                case 5:
                    createOperations();
                    break;
                case 6:
                    try{
                    Doctor doctor1=doctors.get(0);
                    Doctor doctor2=doctors.get(1);
                    RegisterOffice register=new RegisterOffice();

                        for(int i=0;i<3;i++){
                            createCard(patients.get(i),doctor1,register);
                        }
                        for(int i=3;i<patients.size();i++){
                            createCard(patients.get(i),doctor2,register);
                        }
                    }
                    catch(CardAlreadyExistsException e){
                        System.out.println(e.getMessage()+" - "+e.getObjectName());
                    }
                    catch(InputMismatchException e1){
                        System.out.println("Возникла ошибка при создании карточки одного из пациентов.\nВы неверно выбрали действие.");
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("Для создания картчоки не были переданы пациент и/или доктор");
                    }
                default:
                    System.out.println("Вы неверно выбрали следующее действие. Попробуйте еще раз.");
                    continue;
            }
        }

        //region экспорт существующих карт пациентов в файл
        try {
                exportCards();
                cards.clear();
        }
        catch(FileNotFoundException e){
          System.out.println(e.getMessage());
        }
        //endregion

        //region импорт существующих карт пациентов из файла
        try{
            List<PatientCard> listOfImportedCards= importCards();
            for(int i=0;i<listOfImportedCards.size();i++){
                PatientCard card=listOfImportedCards.get(i);
                System.out.println("Карта пациента "+card.getPatient().toString()+" была импортирована.");
                if(!cards.contains(card)){
                    cards.add(card);
                }
                if(!patients.contains(card.getPatient())){
                    patients.add(card.getPatient());
                }
                if(!doctors.contains(card.getDoctor())){
                    doctors.add(card.getDoctor());
                }
            }


        }
        catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        //endregion

    }

    private static void createPatients(){
        Date date1=new GregorianCalendar(2000, Calendar.JULY,6).getTime();
        Date date2=new GregorianCalendar(1999, Calendar.NOVEMBER,26).getTime();
        Date date3=new GregorianCalendar(2000, Calendar.MARCH,30).getTime();
        Date date4=new GregorianCalendar(1969, Calendar.JULY,17).getTime();
        Date date5=new GregorianCalendar(1997, Calendar.AUGUST,13).getTime();

        Patient patient1=new Patient("Ксения","Рогалева",date1,'ж',"+375(44)708-11-38");
        patient1.setAddress("г.Минск,ул.Леонида Беды,4,к.1,918а");

        Patient patient2=new Patient("Светлана","Стёпкина",date2,'ж',"+375(25)536-65-58");
        patient1.setAddress("г.Минск,ул.Леонида Беды,4,к.1,918а");

        Patient patient3=new Patient("Екатерина","Морозова",date3,'ж',"+375(44)238-55-71");
        patient1.setAddress("г.Минск,ул.Леонида Беды,4,к.1,1019а");

        Patient patient4=new Patient("Ирина","Рогалева",date4,'ж',"+375(29)110-47-86");
        patient1.setAddress("г.Слуцк,проезд Садовый,д.12");

        Patient patient5=new Patient("Александр","Адерейко",date5,'м',"+375(25)907-45-80");
        patient1.setAddress("г.Минск,проспект Газеты Правда,18,кв.166");
        patients.add(patient1);
        System.out.println("Пациент "+patient1.toString()+" успешно добавлен.");
        patients.add(patient2);
        System.out.println("Пациент "+patient2.toString()+" успешно добавлен.");
        patients.add(patient3);
        System.out.println("Пациент "+patient3.toString()+" успешно добавлен.");
        patients.add(patient4);
        System.out.println("Пациент "+patient4.toString()+" успешно добавлен.");
        patients.add(patient5);
        System.out.println("Пациент "+patient5.toString()+" успешно добавлен.");
    }

    private static void createDoctors(){
        Date date1=new GregorianCalendar(1976, Calendar.JULY,17).getTime();
        Date date2=new GregorianCalendar(1962, Calendar.OCTOBER,12).getTime();

        Doctor doctor1=new Doctor("Оксана","Орлова",date1,'ж',"+375(29)945-44-81");
        Doctor doctor2=new Doctor("Сергей","Семёнов",date2,'м',"+375(29)104-02-78");

        doctors.add(doctor1);
        System.out.println("Доктор "+doctor1.toString()+" успешно добавлен.");
        doctors.add(doctor2);
        System.out.println("Доктор "+doctor2.toString()+" успешно добавлен.");
    }

    private static void createMedicines(){
        Date date1=new GregorianCalendar(2020, Calendar.MAY,4).getTime();
        Date date2=new GregorianCalendar(2020, Calendar.MAY,11).getTime();
        Date date3=new GregorianCalendar(2020, Calendar.MAY,9).getTime();
        Date date4=new GregorianCalendar(2020, Calendar.MAY,19).getTime();
        Medicine med1=new Medicine("Парацетамол",date1,date2,4.5);
        Medicine med2=new Medicine("Омепразол",date3,date4,9);
        med1.setDose(1);
        med1.setAmountPerDay(3);

        med2.setDose(2.5);
        med2.setAmountPerDay(3);

        medicines.add(med1);
        System.out.println("Лекарство "+med1.getName()+" успешно добавлено.");
        medicines.add(med2);
        System.out.println("Лекарство "+med2.getName()+" успешно добавлено.");

    }

    private static void createProcedures(){

        Date date1=new GregorianCalendar(2020, Calendar.MAY,4).getTime();
        Date date2=new GregorianCalendar(2020, Calendar.MAY,10).getTime();
        Date date3=new GregorianCalendar(2020, Calendar.MAY,5).getTime();
        Date date4=new GregorianCalendar(2020, Calendar.MAY,14).getTime();
        Procedure proc1=new Procedure("Ингаляции",date1,date2,10);
        Procedure proc2=new Procedure("ЛФК",date3,date4,3);

        List<DayOfWeek> days=new ArrayList<DayOfWeek>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.THURSDAY);

        proc1.setCabinet("413-1");
        proc1.setProcedureDays(days);

        days.clear();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.FRIDAY);

        proc2.setCabinet("211");
        proc2.setProcedureDays(days);

        procedures.add(proc1);
        System.out.println("Процедура "+proc1.getName()+" успешно добавлена.");
        procedures.add(proc2);
        System.out.println("Процедура "+proc2.getName()+" успешно добавлена.");
    }

    private static void createOperations(){
        Date date1=new GregorianCalendar(2020, Calendar.MAY,19).getTime();
        Date date2=new GregorianCalendar(2020, Calendar.MAY,19).getTime();
        Operation operation=new Operation("Лигаментотомия",date1,date2,12000);
        operation.setOperatingRoom("999");

        operations.add(operation);
        System.out.println("Операция "+operation.getName()+" успешно добавлена.");
    }

    private static PatientCard createCard(Patient patient, Doctor doctor, RegisterOffice register) throws CardAlreadyExistsException {
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getPatient().equals(patient)){
                String patientInfo=patient.toString();
                throw new CardAlreadyExistsException("Карта пациента уже существует",patientInfo);
            }
        }
        System.out.println("\nПроцесс создания карточки пациента-"+patient.toString()+" доктора-"+doctor.toString()+"...");
        PatientCard card=register.createPatientCard(patient,doctor);
        List<Treatment> treatments=new ArrayList<Treatment>();
        String diagnosis="";
        boolean isRunning=true;
        try{
            while(isRunning){
            System.out.println("Выберите следующее действие: ");
            System.out.println("\t1-назначить таблетки\n\t2-назначить процедуру\n\t3-назначить операцию\n\t4-поставить диагноз\n\t0-ничего");
            Scanner input=new Scanner(System.in);
            int choice=input.nextInt();
            switch(choice){
                case 0:
                    isRunning=false;
                    break;
                case 1:
                    treatments.add(chooseMedicine());
                    break;
                case 2:
                    treatments.add(chooseProcedure());
                    break;
                case 3:
                    treatments.add(chooseOperation());
                    break;
                case 4:
                    diagnosis=setDiagnosis();
                    break;
                default:
                    System.out.println("Вы неверно выбрали следующее действие. Попробуйте еще раз.");
                    continue;
            }
        }
            doctor.setDiagnosisAndTreatment(card,diagnosis,treatments);
            if(!diagnosis.equals("")){
                if(treatments.size()>0 && treatments!=null){
                String treat="";
                for(int i=0;i<treatments.size();i++) {
                    treat += treatments.get(i).getName() + ", ";
                }

                System.out.println("Пациенту "+card.getPatient().toString()+" с диагнозом "+diagnosis+" назначено: "+treat+"\n");
                }
                else {
                System.out.println("Пациенту "+card.getPatient().toString()+" поставлен диагноз "+diagnosis+"\n");
                }
            }

            cards.add(card);
            System.out.println("Карта пациента "+patient.toString()+" была успешно создана");
            return card;
        }
        catch(InputMismatchException e1){
            System.out.println("Возникла ошибка при создании карточки пациента "+patient.toString()+".\nВы неверно выбрали действие. Попробуйте еще раз.");
        }

        return createCard(patient, doctor, register);
    }

    private static Medicine chooseMedicine() throws InputMismatchException{
        Medicine med=new Medicine();
        boolean isRunning=true;
        while(isRunning){
            System.out.println("Выберите из доступных лекарств:");

            for(int i=0;i<medicines.size();i++){
                System.out.println((i+1)+" : \n"+medicines.get(i).toString());
            }
            System.out.println("-1 : \n\tНичего");
            Scanner in=new Scanner(System.in);
            int input=in.nextInt();
            try{
                if (input != -1) {
                    med = medicines.get(input - 1);
                }
                isRunning=false;
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Лекарство выбрано неверно. Попробуйте еще раз.");
                continue;
            }
        }
        System.out.println("Лекарство назначено.");
        return med;
    }

    private static Procedure chooseProcedure()  throws InputMismatchException{
        Procedure proc=new Procedure();
        boolean isRunning=true;
        while(isRunning){
            System.out.println("Выберите из доступных процедур:");
            for(int i=0;i<procedures.size();i++){
                System.out.println((i+1)+" : \n"+procedures.get(i).toString());
            }
            System.out.println("-1 : \n\tНичего");
            Scanner in=new Scanner(System.in);
            int input=in.nextInt();
            try{
                if (input != -1) {
                    proc=procedures.get(input-1);
                }
                isRunning=false;
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Процедура выбрана неверно. Попробуйте еще раз.");
                continue;
            }
        }
        System.out.println("Процедура назначена.");
        return proc;
    }

    private static Operation chooseOperation() throws InputMismatchException{
        Operation op=new Operation();
        boolean isRunning=true;
        while(isRunning){
            System.out.println("Выберите из доступных операций:");
            for(int i=0;i<operations.size();i++){
                System.out.println((i+1)+" : \n"+operations.get(i).toString());
            }
            System.out.println("-1 : \n\tНичего");
            Scanner in=new Scanner(System.in);
            int input=in.nextInt();
            try{
                if (input != -1) {
                    op = operations.get(input - 1);
                }
                isRunning=false;
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Операция выбрана неверно. Попробуйте еще раз.");
                continue;
            }
        }
        System.out.println("Операция назначена.");
        return op;
    }

    private static String setDiagnosis() throws InputMismatchException{

        System.out.println("Введите диагноз, который вы хотите поставить пациенту.");
        Scanner in=new Scanner(System.in);
        String diagnosis=in.next();
        System.out.println("Диагноз поставлен.");
        return diagnosis;
    }

    private static List<PatientCard> importCards() throws IOException,ClassNotFoundException{
        FileInputStream fileInputStream=null;
        System.out.println("Процесс импорта карт пациентов из файла "+filePath);
        try {
            File importCardsFile=new File(filePath);
            fileInputStream=new FileInputStream(importCardsFile);
            ObjectInputStream inputStream=new ObjectInputStream(fileInputStream);
            List<PatientCard> patientCards=(List<PatientCard>)inputStream.readObject();
            return patientCards;
        }
        finally {
            try{
                if(fileInputStream!=null){
                    fileInputStream.close();
                }
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }

    }
    private static void exportCards() throws IOException{
        FileOutputStream fileOutputStream=null;
        System.out.println("Процесс экспорта карт пациентов в файл "+filePath);
        try {
            File exportCardsFile=new File(filePath);
            fileOutputStream=new FileOutputStream(exportCardsFile);
            ObjectOutputStream outputStream=new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(cards);
            System.out.println("Выполнен экспорт "+cards.size()+" карт пациентов в файл");
        }
        finally {
            try{
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
