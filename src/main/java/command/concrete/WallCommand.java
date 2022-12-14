package command.concrete;

import command.Command;
import command.TwitterReceiver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Tweet;

import java.util.ArrayList;
import java.util.Comparator;

@Slf4j
@AllArgsConstructor
public class WallCommand extends Command {

    private TwitterReceiver twitterReceiver;

    @Override
    public void execute() {
        ArrayList<Tweet> wall = new ArrayList<>();
        if (!twitterReceiver.getUserLogged().getFriends().isEmpty()) {
            System.out.printf("%s your mural will now be displayed:%n", twitterReceiver.getUserLogged().getName());
            twitterReceiver.getUserLogged().getFriends().forEach(friend -> wall.addAll(friend.getTweets()));
            wall.addAll(twitterReceiver.getUserLogged().getTweets());
            wall.stream().sorted(Comparator.comparing(Tweet::getTime)).forEach(twitterReceiver::calculateTime);
        } else {
            System.err.println("You don't follow anyone. If you want to see your own tweets, choose the read option");
        }
    }

}
