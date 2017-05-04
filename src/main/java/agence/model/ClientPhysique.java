/**
 * 
 */
package agence.model;

/**
 * @author Seme
 */
public class ClientPhysique extends Client
{
    /**
     * Prénom
     */
    private String prenom;

    /**
     * @param idCli
     */
    public ClientPhysique(int idCli)
    {
        super(idCli);
    }
    public ClientPhysique()
    {
        
    }
    public ClientPhysique(int idCli, String nom, String numTel,String numFax, String eMail, String prenom)
    {
        super();
        this.prenom=prenom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * @param prenom
     *            the prenom to set
     */
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }
}
