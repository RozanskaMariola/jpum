package jpum;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class IteratorsMariola {
    //private static final List<Integer> values = Arrays.asList(1,2,3,4,5);
    private static final List<String> imiona = Arrays.asList("Mariola","Marta","Magda","Alicja","Michał");
    
    private static List<Integer> wartosci = Arrays.asList(1,10,100,1000);
    
    private static void externalIteration() {
        System.out.println("--- External iteration... ");
        
        System.out.println("Wielkosc listy: " + imiona.size());
        
        System.out.println("For: imiona.size(), imiona.get(i): ");
        
        for(int i = 0; i < imiona.size(); ++i){
            System.out.print(i+ " "+ imiona.get(i)+" ");
        }
        
                                                                   System.out.println(); System.out.println();
        
        System.out.println("For: String e: imiona");
        for (String e: imiona){
            System.out.print(" "+ e +" ");
          //  imiona.get(e);
        }
                                                                   System.out.println(); System.out.println();
        //Iterator
        
        System.out.println("Iterator hasNext() next()");
        Iterator itrImion = imiona.iterator();
        while(itrImion.hasNext()){
            Object im = itrImion.next();
            System.out.print(" " + im + " ");
        }
        System.out.println();
        
        Iterator <Integer> itrWartosci = wartosci.iterator();
        while(itrWartosci.hasNext()){
            int war = itrWartosci.next();
            System.out.print(" " + war + " ");
        }
                                                                    System.out.println();
        System.out.println("For: Iterator hasNext() next()");
        
        for(Iterator<String> it = imiona.iterator(); it.hasNext(); ){
            String warFor = it.next();
            System.out.print(" " + warFor + " ");
        }
        
        System.out.println();
        System.out.println();
    }

    private static void internalIteration() {
        System.out.println("--- Internal iteration... ");
        
        imiona.forEach(new Consumer<String> (){
            public void accept(String e){
                System.out.println(e);
            }
        });
        
        wartosci.forEach((Integer e) -> System.out.println(e));
        
        imiona.forEach((String s) -> {
            String i = s + "X";
            System.out.println(i);
        });
        
        imiona.stream().filter(s -> !s.contains("M"))
        .forEach(System.out::println);
        
        wartosci.forEach(new Consumer <Integer>(){
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
            
        });
        

 
        System.out.println();
    }

    public static void main(String[] args) {
        externalIteration();
        internalIteration();
    }
}
