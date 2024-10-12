import io.vn.nguyenduck.auto_generate_api.SemVer;
import io.vn.nguyenduck.auto_generate_api.block.BlockBehavior;

public class ExampleBlockBehavior extends BlockBehavior {

    public ExampleBlockBehavior() {
        super(new SemVer(21), "example:unknown");
    }
}
