package fr.codeapi.quickassistant;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@OptionsPanelController.SubRegistration(
		displayName = "#AdvancedOption_DisplayName_QuickAssistant",
		keywords = "#AdvancedOption_Keywords_QuickAssistant",
		keywordsCategory = "Advanced/QuickAssistant"
)
@NbBundle.Messages({"AdvancedOption_DisplayName_QuickAssistant=QuickAssistant", "AdvancedOption_Keywords_QuickAssistant=quick assistant"})
public final class QuickAssistantOptionsPanelController extends OptionsPanelController {

	private QuickAssistantPanel panel;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private boolean changed;

	@Override
	public void update() {
		getPanel().load();
		changed = false;
	}

	@Override
	public void applyChanges() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				getPanel().store();
				changed = false;
			}
		});
	}

	@Override
	public void cancel() {
		// need not do anything special, if no changes have been persisted yet
	}

	@Override
	public boolean isValid() {
		return getPanel().valid();
	}

	@Override
	public boolean isChanged() {
		return changed;
	}

	@Override
	public HelpCtx getHelpCtx() {
		return null; // new HelpCtx("...ID") if you have a help set
	}

	@Override
	public JComponent getComponent(Lookup masterLookup) {
		return getPanel();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(l);
	}

	private QuickAssistantPanel getPanel() {
		if (panel == null) {
			panel = new QuickAssistantPanel(this);
		}
		return panel;
	}

	void changed() {
		if (!changed) {
			changed = true;
			pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
		}
		pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
	}

}
