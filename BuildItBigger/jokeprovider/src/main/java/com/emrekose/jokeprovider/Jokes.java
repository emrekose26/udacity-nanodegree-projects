package com.emrekose.jokeprovider;

import java.util.concurrent.ThreadLocalRandom;

public class Jokes {
    public static final String[] jokes = {
            "Code never lies, comments do.",
            "Unix is user friendly. It's just very particular about who its friends are.",
            "A programmer puts two glasses on his bedside table before going to sleep. A full one, in case he gets thirsty, and an empty one, in case he doesn't",
            "The fantastic element that explains the appeal of games to many developers is neither the fire-breathing monsters nor the milky-skinned, semi-clad sirens; it is the experience of carrying out a task from start to finish without any change in the user requirements.",
            "There's no place like 127.0.0.1",
            "I don't see women as objects. I consider each to be in a class of her own.",
            "Java programmers wear glasses because they can't C#",
            "A programmer's wife saw a snake laying on their door step, she shouted: Honey, there is a Python on our door step. the husband replied and said, okay I'm coming with my IDLE",
            "Q: What's the object-oriented way to become wealty?\n A: Inheritence",
            "Q: What is a programmer's favorite hangout place?\n A: Foo Bar"
    };

    public static String getRandomJoke() {
        return jokes[ThreadLocalRandom.current().nextInt(0, jokes.length)];
    }
}
