import java.util.Scanner;

interface IMaraton{
    Atleticar najdobroVreme();
    int atleticariOd(String s);
}

class Atleticar{
    private String ime;
    private String pol;
    private int vozrast;
    private double vreme;
    private String zemja;

    public Atleticar(){};
    public Atleticar(String ime, String pol, int vozrast, double vreme, String zemja){
        this.ime = ime;
        this.pol = pol;
        this.vozrast = vozrast;
        this.vreme = vreme;
        this.zemja = zemja;
    }

    // get metodi
    public String getIme(){ return ime; }
    public String getPol(){ return pol; }
    public int getVozrast(){ return vozrast; }
    public double getVreme(){ return vreme; }
    public String getZemja(){ return zemja; }
    // set metodi
    public void setIme(String ime){ this.ime = ime; }
    public void setPol(String pol){ this.pol = pol; }
    public void setVozrast(int vozrast){ this.vozrast = vozrast; }
    public void setVreme(double vreme){ this.vreme = vreme; }
    public void setZemja(String zemja){ this.zemja = zemja; }

    public String toString(){
        return this.ime + "\n" + this.vozrast + "\n" +this.zemja + "\n" +this.vreme;
    }
}

class Maraton implements IMaraton{
    private String mestoOddrzuvanje;
    private int godina;
    private Atleticar[] niza;

    public Maraton(){};
    public Maraton(String mestoOddrzuvanje, int godina, Atleticar[] niza){
        this.mestoOddrzuvanje = mestoOddrzuvanje;
        this.godina = godina;
        this.niza = niza;
    }

    // get metodi
    public String getMesto(){ return mestoOddrzuvanje; }
    public int getGodina(){ return godina; }
    public Atleticar[] getAtleticari(){ return niza; }

    // set metodi
    public void setMesto(String mesto){ this.mestoOddrzuvanje = mesto; }
    public void setGodina(int godina){ this.godina = godina; }
    public void setAtleticari(Atleticar[] niza){  this.niza = niza; }


    @Override
    public String toString(){
        String a = mestoOddrzuvanje + "\n" + godina + "\n";
        for(int i = 0; i < niza.length; i++){
            a += niza[i].toString() + "\n";
        }
        return a;
    }

    @Override
    public Atleticar najdobroVreme(){
        Atleticar finalAt = niza[0];
        for(int i = 0; i < niza.length; i++){
            if(niza[i].getVreme() < finalAt.getVreme()){
                finalAt = niza[i];
            }
        }
        return finalAt;
    }

    @Override
    public int atleticariOd(String s){
        int count = 0;
        for(int i = 0; i < niza.length; i++){
            if(s.equals(niza[i].getZemja()))
                count++;
        }
        System.out.println();
        return count;
    }


}

public class ZadacaMaraton {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        Atleticar[] atleticari = new Atleticar[n];

        String ime;
        String pol;
        int vozrast;
        double vreme;
        String zemja;

        input.nextLine();

        for(int i=0;i<n;i++)
        {
            ime = input.nextLine();
            pol = input.nextLine();
            vozrast = input.nextInt();
            vreme = input.nextDouble();
            input.nextLine();
            zemja = input.nextLine();
            atleticari[i]=new Atleticar(ime,pol,vozrast,vreme,zemja);
        }

        String mesto;
        int godina;
        String zemjaP;
        mesto = input.nextLine();
        godina = input.nextInt();
        input.nextLine();

        Maraton m1 = new Maraton(mesto, godina, atleticari);
        System.out.print(m1.toString());

        zemjaP = input.nextLine();
        System.out.println("Prvo mesto: " + m1.najdobroVreme().toString());
        System.out.println("Ima vkupno " + m1.atleticariOd(zemjaP) + " atleticar/i od " + zemjaP);
    }
}
