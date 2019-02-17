import java.util.Scanner;


//вашиот код
abstract class Patuvanje{
    private String ime;
    private int cena;

    public Patuvanje(String ime, int cena){
        this.ime = ime;
        this.cena = cena;
    }

    // set metodi
    public void setIme(String s){ this.ime = s; }
    public void setCena(int cena){ this.cena = cena; }

    // get metodi
    public String getIme(){ return ime; }
    public int getCena(){ return cena; }

    public abstract int vratiVremeVoDenovi();

    public static int vratiMinCena(Patuvanje[] niza, int n, Patuvanje zaSporedba) {
        int najmalaCena = Integer.MAX_VALUE;
        boolean flag = true;
        for(int i = 0; i < n; i++){
            if(niza[i].vratiVremeVoDenovi() > zaSporedba.vratiVremeVoDenovi()&&najmalaCena > niza[i].getCena()){
                najmalaCena = niza[i].getCena();
                flag = false;
            }
        }

        if(flag){
            return 0;
        }
        else{ return najmalaCena; }
    }
}

class PraznicnoPatuvanje extends Patuvanje{
    private int pocetenDatum;
    private int pocetenMesec;
    private int kraenDatum;
    private int kraenMesec;

    PraznicnoPatuvanje(String ime, int cena, int pocetenDatum, int pocetenMesec, int kraenDatum, int kraenMesec){
        super(ime, cena);
        this.pocetenDatum = pocetenDatum;
        this.pocetenMesec = pocetenMesec;
        this.kraenDatum = kraenDatum;
        this.kraenMesec = kraenMesec;
        try{
            if (pocetenMesec>kraenMesec || (pocetenMesec==kraenMesec&&pocetenDatum>kraenDatum))
                throw new Exception();
        }
        catch(Exception a){
            this.pocetenDatum = kraenDatum;
            this.kraenDatum = pocetenDatum;
            this.pocetenMesec = kraenMesec;
            this.kraenMesec = pocetenMesec;
            System.out.println("Iskluchok");
        }
    }

    public int getPocetenDatum(){ return pocetenDatum; }
    public int getPocetenMesec(){ return pocetenMesec; }
    public int getKraenDatum(){ return kraenDatum; }
    public int getKraenMesec(){ return kraenMesec; }

    public void setPocetenDatum(int pd){ this.pocetenDatum = pd; }
    public void setPocetenMesec(int pm){ this.pocetenMesec = pm; }
    public void setKraenDatum(int kd){ this.kraenDatum = kd; }
    public void setKraenMesec(int km){ this.kraenMesec = km; }

    @Override
    public int vratiVremeVoDenovi(){
        if(pocetenMesec == kraenMesec){
            return kraenDatum - pocetenDatum;
        }
        else{
            return 30- pocetenDatum+ kraenDatum;
        }
    }



}

class GodishenOdmor extends Patuvanje{
    private int denovi;

    //GodishenOdmor(){ super(); }

    public GodishenOdmor(String ime, int cena, int denovi){
        super(ime, cena-1000);
        this.denovi = denovi;
    }

    public int getDenovi(){ return denovi; }
    public void setDenovi(int denovi){ this.denovi = denovi; }

    @Override
    public int vratiVremeVoDenovi(){
        return denovi;
    }
}

public class Test {


    public static void main(String[] args) {


        int n;
        Scanner in=new Scanner(System.in);
        n=in.nextInt();

        Patuvanje nizaPatuvanje[]=new Patuvanje[n];

        for (int i=0;i<n;i++){
            int tip=in.nextInt();
            if (tip==0){
                String ime=in.next();
                int cena =in.nextInt();
                int vreme=in.nextInt();
                nizaPatuvanje[i]=new GodishenOdmor(ime,cena,vreme);
            }
            else {
                String ime=in.next();
                int cena =in.nextInt();
                int pocD=in.nextInt();
                int pocM=in.nextInt();
                int krajD=in.nextInt();
                int krajM=in.nextInt();
                nizaPatuvanje[i]=new PraznicnoPatuvanje(ime,cena,pocD,pocM, krajD,krajM);

            }
        }

        //решение на барање 1
        int prosek = 0;
        for (int i = 0; i<n; i++){
            prosek += nizaPatuvanje[i].vratiVremeVoDenovi();
            if (nizaPatuvanje[i] instanceof PraznicnoPatuvanje){
                PraznicnoPatuvanje p = (PraznicnoPatuvanje)nizaPatuvanje[i];
                if (p.getPocetenMesec() == 6)
                    System.out.print(nizaPatuvanje[i].getIme()+" ");
            }

        }
        System.out.print("\n");

        //решение на барање 2
        System.out.println((float)prosek/n);

        //решение на барање 3
        String ime = in.next();
        int cena = in.nextInt();
        int vremetraenje = in.nextInt();
        GodishenOdmor odmor = new GodishenOdmor(ime, cena, vremetraenje);

        //решение на барање 4
        int podolgiOd = odmor.vratiMinCena(nizaPatuvanje, nizaPatuvanje.length, odmor);
        System.out.println(podolgiOd);

    }

}
