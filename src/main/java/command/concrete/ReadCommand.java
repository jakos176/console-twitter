package command.concrete;

import command.Command;
import command.TwitterReceiver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ReadCommand extends Command {

    private TwitterReceiver twitterReceiver;

    @Override
    public void execute() {
        System.out.printf("%s this is your personal wall: ", twitterReceiver.getUserLogged().getName());
        if (!twitterReceiver.getUserLogged().getTweets().isEmpty()) {
            twitterReceiver.getUserLogged().getTweets().forEach(twitterReceiver::calculateTime);
            System.out.println("End of the wall");
        } else {
            System.err.printf("%s you have not published anything!", twitterReceiver.getUserLogged().getName());
        }
    }
}
