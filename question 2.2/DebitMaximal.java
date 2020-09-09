    package question2;
    
    import question1.Contributeur;
    import question1.GroupeDeContributeurs;
    import question1.Visiteur;
    import question1.Cotisant;
    
    public class DebitMaximal implements Visiteur<Integer>{
    
      public Integer visite(Contributeur c){
         return c.solde();
      }
      
      public Integer visite(GroupeDeContributeurs g){
          
       
        
         int res = 0;
          Cotisant c = g.getChildren().get(0);
         
            
            for(Cotisant cc :  g.getChildren())
            {
                int innerRes = -1;
                if(cc instanceof GroupeDeContributeurs)
                  {  innerRes = visite((GroupeDeContributeurs)cc);
                       if(innerRes < res) res = innerRes;
                    }
                 else
                 {
                     if(cc.solde() <res) res = cc.solde();
                    }
                
 
                
                  
            }
            
            return res;
            
            
         
            
          
        }
        
     
  
}