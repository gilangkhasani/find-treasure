package sg.fwd.techTest.be.web.model;

/*
Author : Gilang Permadi Khasani
Description : Command for json request
Version : 0.1
Last Update : 18-05-2021
 */
public class Command {

    String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "Grid [command=" + command + "]";
    }

}
