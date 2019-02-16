public class ServerStart {
    /**
     * Method made for just starting the server. Modify the port if it won't work on your computer.
     * Corresponds to port number in web file (TODO insert web file here)
     */
    public static void main(String[] args) {
        String port = String.valueOf(15255);

        new Server().run(port);
    }
}
