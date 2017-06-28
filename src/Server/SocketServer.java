package Server;

import Communication.ConsultationCommunicate;
import Communication.PatientCommunicate;
import Communication.UserCommunicate;
import Server.BusinessLogic.AdminBL;
import Server.BusinessLogic.DoctorBL;
import Server.BusinessLogic.SecretaryBL;
import Server.model.Consultation;
import Server.model.Patient;
import Server.model.User;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by matei on 6/26/2017.
 */
public class SocketServer implements Runnable {

    private Socket connection;
    private int port;

    public SocketServer(Socket connection, int port) {
        this.connection = connection;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            readFromClient(connection);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
        }
    }

    private void readFromClient(Socket client) throws IOException, ClassNotFoundException, SQLException {
        ObjectInputStream inStream = null;
        inStream = new ObjectInputStream(client.getInputStream());

        String operation = inStream.readObject().toString();
        System.out.println(operation);
        AdminBL admin = new AdminBL();
        SecretaryBL secretary = new SecretaryBL();
        DoctorBL doctor = new DoctorBL();

        switch (operation) {
            case "login":
                UserCommunicate userCommunicate = (UserCommunicate) inStream.readObject();
                User user = new User();
                user.setUsername(userCommunicate.getUsername());
                user.setPassword(userCommunicate.getPassword());
                user.readUser();
                userCommunicate.setRoleId(user.getRoleId());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                writeObject(client, userCommunicate);
                connection.close();
                break;

            case "readPatients":
                readPacient(client);
                break;
            case "createPatient":
                System.out.println("Create Pacient");
                PatientCommunicate pacientC = (PatientCommunicate) inStream.readObject();
                Patient patient = new Patient(pacientC.getPatientId(),
                        pacientC.getName(), pacientC.getpNC(), pacientC.getIdentityCard(),
                        pacientC.getDateOfBirth(), pacientC.getAdress());
                admin.createPatient(patient);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                readPacient(client);
                break;

            case "deletePatient":
                System.out.println("Delete Patient");
                PatientCommunicate pacientCDelete = (PatientCommunicate) inStream.readObject();
                Patient patientDelete = new Patient();
                patientDelete.setPatientId(pacientCDelete.getPatientId());
                admin.deletePatient(patientDelete);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                readPacient(client);
                break;

            case "updatePacients":
                ArrayList<Patient> pacientsUpdate = new ArrayList();
                ArrayList<PatientCommunicate> pacientsCUpdate = new ArrayList();
                pacientsCUpdate = (ArrayList<PatientCommunicate>) inStream.readObject();
                for (int i = 0; i < pacientsCUpdate.size(); i++) {
                    Patient pacientUpdate = new Patient(pacientsCUpdate.get(i).getPatientId(), pacientsCUpdate.get(i).getName(), pacientsCUpdate.get(i).getIdentityCard(), pacientsCUpdate.get(i).getpNC(),
                            pacientsCUpdate.get(i).getDateOfBirth(), pacientsCUpdate.get(i).getAdress());
                    admin.updatePatient(pacientUpdate);
                }
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                readPacient(client);
                break;
            case "readConsultations":
                readConsultation(client);
                break;
            case "createConsultation":
                System.out.println("Create consultation");
                ConsultationCommunicate consultationC = (ConsultationCommunicate) inStream.readObject();
                Consultation cons = new Consultation(
                        consultationC.getConsultationId(), consultationC.getDoctorId(), consultationC.getPatientId(),
                        consultationC.getDateCond(), consultationC.getDescription());
                System.out.println(cons.getDoctorId());
                secretary.createConsultation(cons);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                readConsultation(client);
                break;
            case "doctorsConsultations":
                UserCommunicate usrDoctor = (UserCommunicate) inStream.readObject();
                readDoctorsConsultations(client, usrDoctor);
                break;
            case "deleteConsultation":
                System.out.println("Delete Consultation");
                ConsultationCommunicate consCDelete = (ConsultationCommunicate) inStream.readObject();
                Consultation consDelete = new Consultation();
                consDelete.setConsultationId(consCDelete.getConsultationId());
                secretary.deleteConsultation(consDelete);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                readConsultation(client);
                break;
        }
    }

    private void sendMessage(Socket client, String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(client.getOutputStream()));
        writer.write(message);
        writer.flush();
    }

    public void writeObject(Socket client, Object obj) throws IOException {

        ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
        output.writeObject(obj);
        output.flush();
    }

    public void readObject(Socket client) {
        try {
            ObjectInputStream inStream = null;
            inStream = new ObjectInputStream(client.getInputStream());
            User us = (User) inStream.readObject();
            System.out.println("Object received: " + us);
            System.out.println("Object received: " + inStream.readObject());

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    public void readPacient(Socket client) throws IOException, SQLException {
        AdminBL admin = new AdminBL();
        System.out.println("READ CLIENT");
        ArrayList<Patient> patients = new ArrayList();
        ArrayList<PatientCommunicate> pacientsC = new ArrayList();
        patients = (ArrayList<Patient>) admin.readPatients();
        for (int i = 0; i < patients.size(); i++) {
            PatientCommunicate patient = new PatientCommunicate(patients.get(i).getPatientId(), patients.get(i).getName(), patients.get(i).getIdentityCard(), patients.get(i).getpNC(),
                    patients.get(i).getDateOfBirth(), patients.get(i).getAdress());
            pacientsC.add(patient);
        }
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        System.out.println("READ CLIENT before");
        writeObject(client, pacientsC);
        connection.close();
    }


    public void readConsultation(Socket client) throws IOException, SQLException {
        SecretaryBL secretary = new SecretaryBL();
        System.out.println("READ Consultations");
        ArrayList<Consultation> consultation = new ArrayList();
        ArrayList<ConsultationCommunicate> consultationC = new ArrayList();
        consultation = (ArrayList<Consultation>) secretary.readConsultations();
        for (int i = 0; i < consultation.size(); i++) {
            ConsultationCommunicate cons = new ConsultationCommunicate(consultation.get(i).getConsultationId(),
                    consultation.get(i).getDoctorId(),
                    consultation.get(i).getPatientId(),
                    consultation.get(i).getDateCond(),
                    consultation.get(i).getDescription());
            System.out.println(cons.getConsultationId());
            consultationC.add(cons);
        }
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        System.out.println(consultationC.get(0).getConsultationId());
        System.out.println(consultationC.get(1).getConsultationId());
        writeObject(client, consultationC);
        connection.close();
    }

    public void readDoctorsConsultations(Socket client, UserCommunicate user) throws IOException, SQLException {
        SecretaryBL secretary = new SecretaryBL();
        System.out.println("READ Doctors Consultation");
        ArrayList<Consultation> consultation = new ArrayList();
        ArrayList<ConsultationCommunicate> consultationC = new ArrayList();
        User u = new User();
        u.setUserId(user.getUserId());
        consultation = (ArrayList<Consultation>) secretary.readDoctorsConsultation(u);
        for (int i = 0; i < consultation.size(); i++) {
            ConsultationCommunicate cons = new ConsultationCommunicate(consultation.get(i).getConsultationId(),
                    consultation.get(i).getDoctorId(),
                    consultation.get(i).getPatientId(),
                    consultation.get(i).getDateCond(),
                    consultation.get(i).getDescription());
            System.out.println(cons.getConsultationId());
            consultationC.add(cons);
        }
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        writeObject(client, consultationC);
        connection.close();
    }
}
