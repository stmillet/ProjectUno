public class ServerStart {
    /**
     * Class made for just starting the server. Modify the port if it's not working on your computer.
     * Hit the play button to the left of the class name above, then navigate to localhost:15255 in your browser
     *
     * It should load up everything! If you go to localhost:15255/anything , a 404 error will show up.
     * We can modify that 404 page later to make it more fun.
     */
    public static void main(String[] args) {
        String port = String.valueOf(15255);

        new Server().run(port);
    }
}
