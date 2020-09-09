
package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>, Cloneable{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
    this.liste =  new ArrayList<Cotisant>();
  }
  
  public void ajouter(Cotisant cotisant){
      if(cotisant == null) return ;
    this.liste.add(cotisant);
    cotisant.setParent(this);
  }
  
  
  public int nombreDeCotisants(){
      if(liste == null) return 0;
    int nombre = 0;
    
    for(Cotisant c :  liste)
    {
        nombre += c.nombreDeCotisants();
    }
    
    return nombre;
  }
  
  public String toString(){
    String str = new String();
    
    Iterator<Cotisant> it =  iterator();
    while (it.hasNext())
    {
        Cotisant c =  it.next();
        str += c.toString();
    }
    return str;
  }
  
  public List<Cotisant> getChildren(){
     
    return liste;
  }
  
  public void debit(int somme) throws SoldeDebiteurException{
      if(liste ==null) return ;
      if(somme > solde()) throw new SoldeDebiteurException("Impossible de débiter cette somme");
        if(somme < 0) throw new RuntimeException("Inférieur à  0!!");
      
       for(Cotisant c : liste)
       {
           c.debit(somme);
        }
        
  }
  
  public void credit(int somme){
      if(liste == null) return;
      if(somme < 0) throw new RuntimeException("Inférieur à  0!!");
      
       for(Cotisant c : liste)
       {
           c.credit(somme);
        }
    }
  
  public int solde(){
      if(liste == null) return 0;
    int solde = 0;
    
    for(Cotisant c : liste)
    {
        solde += c.solde();
    }
    
    return solde;
    
  }
  
  // mÃ©thodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  

}
