import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class BlockBehaviorTest {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    void EmptyBlock() {
        var x = new ExampleBlockBehavior();
        logger.info(x.toString(4));
    }
}
