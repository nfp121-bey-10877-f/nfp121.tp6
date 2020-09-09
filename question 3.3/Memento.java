package question3;

import question1.*;
import java.util.*;

public class Memento {
     // Note : Un usage du patron Memento, 
     //        d’un premier visiteur pour la sauvegarde et 
     //        d’un second pour la restitution du composite, 
     //        représentent une solution possible. 
     
     private Cotisant state;
     public Memento(Cotisant c) {
         
     
         this.state = copy(c); 
          
     
    }

     public void setState(Cotisant c) {
       this.state = c;
     }
     
     public Cotisant getState ()
     {
         return this.state;
        }
     
     public Cotisant copy(Cotisant c)
     {
         if( c instanceof Contributeur)
         {
             Contributeur co =  (Contributeur)c;
             return new Contributeur(co.nom(), co.solde());
            }
            else
            {
                GroupeDeContributeurs g = (GroupeDeContributeurs)c;
                GroupeDeContributeurs nouveau = new GroupeDeContributeurs(g.nom());
                for(Cotisant co : g.getChildren())
                {
                    nouveau.ajouter(copy(co));
                }
                return nouveau;
                
            }
        }
    
    }