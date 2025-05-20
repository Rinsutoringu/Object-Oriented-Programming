package local.ui.homepage.subpage.leftbar.count;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUILogical;

public class countLogic extends StandardUILogical {

    private countUI countui;

    private errorHandler eh = errorHandler.getInstance();

    public countLogic() {
        super();
        try {
            countui = new countUI();
            putPL("count", getThis().getPanel("count"));
            defaultView();
            addButtonAction();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    public void defaultView() {

    }

    @Override
    public void addButtonAction() {
    }

    @Override
    public countUI getThis() {
        return countui;
    }
}