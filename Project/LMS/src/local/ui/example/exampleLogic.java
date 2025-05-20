package local.ui.example;

import javax.swing.JPanel;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUILogical;

public class exampleLogic extends StandardUILogical {


    private exampleUI exampleui;
    private JPanel examplePL;
    private errorHandler eh = errorHandler.getInstance();
    public exampleLogic() {

        super();
        try {
            exampleui = new exampleUI();
            putPL("examplewindow", getThis().getPanel("examplewindow"));
            putCP("example1", exampleui.getPanel("example1"));
            defaultView();
            addButtonAction();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    public void defaultView() {

        try {
            show(getThis(), this.examplePL);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        
    }

    @Override
    public void addButtonAction() {
        
        exampleui.getButton("example").addActionListener(e->{

            try {
                System.out.println("example button clicked");
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }

        }
        );

    }
    @Override
    public exampleUI getThis() {
        return exampleui;
    }
}
