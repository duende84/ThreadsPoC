package lib;

/**
 *
 * @author duende84
 */
public class Producer extends Thread {

    private final SynchronizedBuffer buffer;
    private final int id;
    private final String username;
    private final String token;

    public Producer(SynchronizedBuffer buffer, int id, String username, String token) {
        super("Producer_"+id);
        this.buffer = buffer;
        this.id = id;
        this.username = username; 
        this.token = token;
    }

    @Override
    public void run() {
        try {
            while(true){
                System.out.println(getName());
                buffer.setData(Service.getGitHubRepoUrl(this.username, this.token));
                Thread.sleep((int) (Math.random() * 5001));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        System.out.println("End " + getName() + "."+"\n");

    }
}
