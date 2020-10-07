import java.util.LinkedList;

public class Studente
{
    private String nome;
    private LinkedList<Voto> voti;

    //GET & SET
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LinkedList<Voto> getVoti() {
        return voti;
    }


    //COSTRUTTOREROTTURTSOC
    public Studente(String n)
    {
        nome = n;
        voti = new LinkedList<>();
    }

    //METODI
    public void addVoto(Voto v)
    {
        voti.add(v);
    }
}

 class Voto
{
    int voto;
    String studente;
    private boolean flag;

    public Voto(int v, String stud)
    {
        voto = v;
        studente = stud;

    }
    public Voto(int v,boolean flag,String Stud)
    {
        this.flag = flag;
            voto = v;
            studente = Stud;



    }



    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();

        if(!flag)
            strb.append(voto);
        else {
            switch (voto) {
                case 0:
                default:
                    strb.append("1");
                    break;
                case 1:
                    strb.append("2");
                    break;
                case 2:
                    strb.append("3");
                    break;
                case 3:
                    strb.append("3+");
                    break;
                case 4:
                    strb.append("3.5");
                    break;
                case 5:

                    strb.append("4-");
                    break;
                case 6:
                    strb.append("4");
                    break;
                case 7:
                    strb.append("4+");
                    break;
                case 8:
                    strb.append("4.5");
                    break;
                case 9:
                    strb.append("5-");
                    break;
                case 10:
                    strb.append("5");
                    break;
                case 11:
                    strb.append("5+");
                    break;
                case 12:
                    strb.append("5.5");
                    break;
                case 13:
                    strb.append("6-");
                    break;
                case 14:
                    strb.append("6");
                    break;
                case 15:
                    strb.append("6+");
                    break;
                case 16:
                    strb.append("6.5");
                    break;
                case 17:
                    strb.append("7-");
                    break;
                case 18:
                    strb.append("7");
                    break;
                case 19:
                    strb.append("7+");
                    break;
                case 20:
                    strb.append("7.5");
                    break;
                case 21:
                    strb.append("8-");
                    break;
                case 22:
                    strb.append("8");
                    break;
                case 23:
                    strb.append("8+");
                    break;
                case 24:
                    strb.append("8.5");
                    break;
                case 25:
                    strb.append("9-");
                    break;
                case 26:
                    strb.append("9");
                    break;
                case 27:
                    strb.append("9+");
                    break;
                case 28:
                    strb.append("9.5");
                    break;
                case 29:
                    strb.append("10");
                    break;
                case 30:
                    strb.append("10 & lode");
                    break;
            }
        }

        return  strb.toString();
    }
}
