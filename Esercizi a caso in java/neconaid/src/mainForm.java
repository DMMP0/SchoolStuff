import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class mainForm {

    //servowo per la form
    private JPanel panel1;
    private JPanel upperPanel;
    private JPanel lowerPanel;
    private JComboBox interroga;
    private JComboBox voto;
    private JButton salva;
    private JButton stampa;
    private JTextArea log;

    //servono per il programma
    private int currentStudent;
    private LinkedList<Studente> students;
    private LinkedList<String> valoreVoti;
    private boolean changeST;
    private boolean changeV;
    private File voti;
    private File studs;
    private File save;

    private Scanner sc2;
    private Scanner sc3;

    private String saveStateString = " ";


    public mainForm() throws FileNotFoundException {
        createUIComponents();
    }



//non modificare
    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("mainForm");
        frame.setContentPane(new mainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //crea tutto
    private void createUIComponents() throws FileNotFoundException {

    //lista con gli studenti
        students = new LinkedList<>();

        //lista coi votee
        valoreVoti = new LinkedList<>();
        valoreVoti.add("1");
        valoreVoti.add("2");
        valoreVoti.add("3");
        valoreVoti.add("3+");
        valoreVoti.add("3.5");
        valoreVoti.add("4-");
        valoreVoti.add("4");
        valoreVoti.add("4+");
        valoreVoti.add("4.5");
        valoreVoti.add("5-");
        valoreVoti.add("5");
        valoreVoti.add("5+");
        valoreVoti.add("5.5");
        valoreVoti.add("6-");
        valoreVoti.add("6");
        valoreVoti.add("6+");
        valoreVoti.add("6.5");
        valoreVoti.add("7-");
        valoreVoti.add("7");
        valoreVoti.add("7+");
        valoreVoti.add("7.5");
        valoreVoti.add("8-");
        valoreVoti.add("8");
        valoreVoti.add("8+");
        valoreVoti.add("8.5");
        valoreVoti.add("9-");
        valoreVoti.add("9");
        valoreVoti.add("9+");
        valoreVoti.add("9.5");
        valoreVoti.add("10");
        valoreVoti.add("10 & lode");

        //controllo per salva ed interroga
        changeST = false;
        changeV = false;

        //strb
        StringBuilder sb = new StringBuilder();

        //file e scanner
        voti = new File(".\\voti.txt");
        studs = new File(".\\studenti.txt");
        save = new File(".\\savestate.sav");

        sc2 = new Scanner(studs);
        sc3 = new Scanner(save);
        sc3.useDelimiter(";");

        //legge il savestate
        while (sc3.hasNext()) {
            String token = sc3.next();
            sb.append(token);
            sb.append("\n");

        }

        saveStateString = sb.toString();
        currentStudent = -1;


        //costruisce l' ui
        //è abbastanza spartana Nechifor, dovresti modificarla un po'
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(3, 2));
        lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(1, 1));
        interroga = new JComboBox();
        voto = new JComboBox();
        salva = new JButton();
        stampa = new JButton();
        log = new JTextArea();
        panel1.add(upperPanel);
        panel1.add(lowerPanel);
        interroga.setName("INTERROGA");
        voto.setName("VOTO");
        salva.setText("SALVA");
        stampa.setText("STAMPA");
        Label L1 = new Label("Interroga", SwingConstants.CENTER);
        Label L2 = new Label("Voto", SwingConstants.CENTER);
        upperPanel.add(L1);
        upperPanel.add(L2);
        upperPanel.add(interroga);
        upperPanel.add(voto);
        upperPanel.add(salva);
        upperPanel.add(stampa);
        lowerPanel.add(log);
        log.setText(saveStateString);


        sc2.useDelimiter(";");

        //todo nulla?

        students = new LinkedList<>();

        //riempono la lista per il componente che scorre
        while (sc2.hasNext()) {
            String token = sc2.next();
            students.add(new Studente(token));
        }

        for (Studente st : students) {
            interroga.addItem(st.getNome());

        }
        for (String st : valoreVoti) {
            voto.addItem(st);

        }


        //sezione degli action listener NB: leggiti cosa sono
        interroga.addActionListener(e -> Interroga(interroga.getSelectedIndex()));

        voto.addActionListener(e -> Voto(new Voto(voto.getSelectedIndex(), true, students.get(currentStudent).getNome())));

        salva.addActionListener(e -> {
            try {
                Salva();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        stampa.addActionListener(e -> {
            try {
                Stampa();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }


    public void Interroga(int st) {
        changeV = false;

        if (changeST)
            log.append("Lo studente è stato cambiato in" + students.get(st).getNome() + "\n");
        else
            log.append("\nLo studente " + students.get(st).getNome() + " è appena stato interrogato\n");
        currentStudent = st;
        changeST = true;
        // saveStateString = log.getText();    OwO wat dis
    } //ok

    public void Voto(Voto v) {
        changeST = false;
        if (currentStudent != -1) {


            students.get(currentStudent).addVoto(v);
            if (changeV) {
                log.append("Il voto è stato cambiato in: " + v.toString() + "\n");
            } else {
                log.append("Gli è stato assegnato " + v.toString() + "\n");
                changeV = true;
            }
        }
        // saveStateString = FIGHTING GOOOOOOOOOOOOOOOOOOOOOOOLD;
    } //ok

    public void Salva() throws IOException {
        saveStateString = log.getText();
        File f = new File(".\\savestate.sav");
        BufferedWriter fw = new BufferedWriter(new FileWriter(f));
        fw.write(saveStateString);
        fw.flush();
        fw.close();

    }

    public void Stampa() throws IOException {
        saveStateString = log.getText();
        File f = new File(".\\Voti.txt");
        BufferedWriter fw = new BufferedWriter(new FileWriter(f));
        fw.append(saveStateString);
        fw.flush();
        fw.close();
        saveStateString = " ";
        log.setText(saveStateString);
        f = new File(".\\savestate.sav");
        fw = new BufferedWriter(new FileWriter(f));
        fw.write(saveStateString);
        fw.flush();
        fw.close();

    }


}

