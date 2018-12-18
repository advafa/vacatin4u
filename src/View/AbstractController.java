package View;

import Main.ViewModel;
import javafx.scene.input.MouseEvent;

public class AbstractController {
    protected ViewModel viewModel;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
