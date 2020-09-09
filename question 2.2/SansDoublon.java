package question2;

import question1.*;
import java.util.Set;
import java.util.TreeSet;

public class SansDoublon implements Visiteur<Boolean>{
  public Boolean visite(Contributeur c){
    return !c.nom().equals(c.getParent().nom());
  }
  
  public Boolean visite(GroupeDeContributeurs g){
      
      boolean sansDoublon = true;
      for(Cotisant c : g.getChildren())
      {
          String nom = c.nom();
          if(c.getParent() != null)
            if(nom.equals(c.getParent().nom())) return false;
          for(Cotisant c2 : g.getChildren())
          {
              if((!(c2 ==c) && (c2.nom().equals(nom)))) return false;
              if(c2.getParent() != null)
              if (c2.nom().equals(c2.getParent().nom())) return false;
              
            }
        }
        
       return sansDoublon;
      
  }
  
}