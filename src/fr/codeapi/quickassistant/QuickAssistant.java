package fr.codeapi.quickassistant;

import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileUtil;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.util.Utilities;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(
		category = "File",
		id = "fr.codeapi.QuickAssistant.QuickAssistant"
)
@ActionRegistration(
		displayName = "#CTL_QuickAssistant"
)
@ActionReferences({
	@ActionReference(path = "Menu/File", position = 300),
	@ActionReference(path = "Shortcuts", name = "SD-L")
})
@NbBundle.Messages("CTL_QuickAssistant=QuickAssistant")
public final class QuickAssistant implements ActionListener {

	private static final String TEMPLATES_FOLDER = "Templates";
	private static final String TEMPLATES_DEFAULT = TEMPLATES_FOLDER+"/Other/file";
	private ArrayList<String[]> data = new ArrayList<String[]>();;
	private String[] selected;
	private int cptTry = 0;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (setFocusOnComponent()) {
			cptTry = 0;
			final JDialog jp = new JDialog();
			jp.setSize(400, 50);
			jp.setLocationRelativeTo(null);
			jp.setUndecorated(true);
			jp.setVisible(true);
			jp.setBackground(Color.DARK_GRAY);
			jp.getRootPane().setBorder( BorderFactory.createLineBorder(Color.DARK_GRAY) );
			jp.addWindowFocusListener(new WindowFocusListener() {

				@Override
				public void windowGainedFocus(WindowEvent e) {

				}

				@Override
				public void windowLostFocus(WindowEvent e) {
					jp.dispose();
				}
			});

			final JPanel pan = new JPanel();
			pan.setBackground(Color.DARK_GRAY);
			pan.setLayout(null);


			Font font = new Font("Arial", 0, 18);

			final JLabel lb = new JLabel("<html></html>");
			lb.setFont(font);
			lb.setForeground(Color.WHITE);
			lb.setBounds(0, 50, 400, 50);
			lb.setBorder(new EmptyBorder(10, 10, 10, 10));
			lb.setSize(400, 50);

			final JLabel lb2= new JLabel("<html></html>");
			lb2.setFont(font);
			lb2.setForeground(Color.WHITE);
			lb2.setBounds(0, 100, 400, 50);
			lb2.setBorder(new EmptyBorder(10, 10, 10, 10));
			lb2.setSize(400, 50);


			font = new Font("Arial", 0, 20);
			final JTextField tf = new JTextField();
			tf.setFont(font);
			tf.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
			tf.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {

				}

				@Override
				public void keyPressed(KeyEvent e) {
					restoreParams();
					selected = null;
					if (e.getKeyCode()==27) {
						jp.dispose();
					}
					else if (e.getKeyCode()==10) {
						String str = tf.getText();
						detection(str, true);
						jp.dispose();
					}
					else if (e.getKeyCode()==e.VK_TAB) {
						String str = tf.getText();
						tf.setText(tabDetection(str, true));
						keyReleased(e);
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					String str = tf.getText();
					if (str.isEmpty()) {
						jp.setSize(400, 50);
					}
					else {
						String detect = detection(str, false);
						lb.setText(detect);
						jp.setSize(400, 100);
						if (!detect.isEmpty() && str.contains(":")) {
							String tabDetect = tabDetection(str, false);
							if (!tabDetect.isEmpty()) {
								lb2.setText(tabDetect);
								jp.setSize(400, 150);
							}
						}
					}
				}
			});
			tf.setSize(400, 50);
			tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			pan.add(tf);
			pan.add(lb);
			pan.add(lb2);
			jp.add(pan);
		}
		else {
			actionPerformed(e);
		}
	}
	
	private boolean setFocusOnComponent() {
		String completePath = originPath("");
		if (completePath==null && cptTry<2) {
			cptTry++;
			for (Mode m : WindowManager.getDefault().getModes()) { 
				for (TopComponent tp : WindowManager.getDefault().getOpenedTopComponents(m)) { 
					if (tp.getClass().toString().equals("class org.netbeans.core.multiview.MultiViewCloneableTopComponent") || tp.getName().equals("Projects")) {
						tp.requestActive();
						break;
					}
				} 
			} 
			
			return false;
		}
		return true;
	}
	
	private String detection(String str, Boolean exec) {
		String description = "<html>";
		if (str.startsWith("f:")) {
			description += "Créer le fichier";
			String name = str.substring(2);
			if (exec) {
				createFile(name);
			}
			else {
				description += pathInfo(name, TEMPLATES_DEFAULT, false);
			}
		}
		else if (str.startsWith("d:")) {
			String name =  str.substring(2);
			description += "Créer le dossier";
			if (exec) {
				createFolder(name);
			}
			else {
				description += pathInfo(name, TEMPLATES_DEFAULT, true);
			}
		}
		else {
			for (String[] data1 : data) {
				if (str.startsWith(data1[2]+":")) {
					if ("0".equals((String)data1[1])) {
						description += data1[0];
						selected = data1;
						int length = ((String)data1[2]).length();
						String name = str.substring(length+1);
						if (exec) {
							createFile(name, (String)data1[3]);
						}
						else {
							description += pathInfo(name, data1[3], false);
						}
						break;
					}
					else if ("1".equals((String)data1[1])) {
						description += data1[0];
						selected = data1;
						int length = ((String)data1[2]).length();
						String name = str.substring(length+1);
						if (exec) {
							openProject(getFirstProject(name, data1[3], exec));
							//createFile(name, (String)data1[3]);
						}
						else {
							description += getFirstProject(name, data1[3], exec);
						}
					}
				}
			}
		}
		return description+"</html>";
	}
	
	private String tabDetection(String str, boolean exec) {
		String description = "";
		String[] dec = str.split(":");
		if (dec.length==2) {
			String name = dec[1];
			String completePath = createCompletePath(originPath(name), name);
			if (completePath!=null) {
				File f = new File(completePath);
				name = cleanName(name, TEMPLATES_DEFAULT, true);
				File[] list = f.listFiles();
				if (list!=null) {
					for (File list1 : list) {
						if (list1.isDirectory()) {
							if (list1.getName().startsWith(name)) {
								if (exec) {
									description = str.replaceFirst(""+name+"$", list1.getName()+"/");
								}
								else {
									description = "Tab pour aller dans "+list1.getName();
								}
								break;
							}
						}
					}
				}
			}
		}
		if (exec && description.isEmpty()) {
			description = str;
		}
		return description;
	}
	
	private String pathInfo(String name, String template, boolean directory) {
		String prefix = "<div style='font-size:10px;'>";
		String suffix = "</div>";
		String completePath = createCompletePath(originPath(name), name);
		
		if (completePath!=null) {
			return prefix+pathFromProjet(completePath)+"/<b>"+cleanName(name, template, directory)+"</b>"+suffix;
		}
		return prefix+"Chemin indisponible"+suffix;
	}
	
	
	private String pathFromProjet(String path) {
		if (path!=null) {
			Project p = getCurrentProject();
			ProjectInformation pi = ProjectUtils.getInformation(p);
			path = path.replaceAll(p.getProjectDirectory().getPath(), pi.getName()+" : ");
		}
		return path;
	}
	
	
	private void openProject(String path) {
		if (path!=null) {
			File f = new File(path);
			if (f.isDirectory()) {
				FileObject tmp = FileUtil.toFileObject(f);
				Project project;
				try {
					project = ProjectManager.getDefault().findProject(tmp);
					Project[] array = new Project[1];
					array[0] = project;
					OpenProjects.getDefault().open(array, false);
				} catch (IOException ex) {
					Exceptions.printStackTrace(ex);
				} catch (IllegalArgumentException ex) {
					Exceptions.printStackTrace(ex);
				}
			}
		}
	}
	
	private String getFirstProject(String name, String path, boolean exec) {
		String prefix = "<div style='font-size:10px;'>";
		String suffix = "</div>";
		File f = new File(path);
		File[] list = f.listFiles();
		if (list!=null && !name.isEmpty()) {
			for (File list1 : list) {
				String n = list1.getName();
				if (list1.isDirectory() && n.startsWith(name)) {
					String projetPath = Paths.get(f.getPath(), n).toString();
					if (exec) {
						return projetPath;
					}
					else {
						return " "+n+prefix+projetPath+suffix;
					}
				}
			}
		}
		return "";
	}
	
	private String cleanName(String name, String template, boolean directory) {
		if (name.contains("/")) {
			String[] dec =name.split("/");
			if (dec.length>0) {
				if (name.endsWith("/")) {
					name = "";
				}
				else {
					name = dec[dec.length-1];
				}
			}
		}
		if (!directory) {
			FileObject firstTemplate = FileUtil.getConfigFile(template);
			if (!name.contains(".")) {
				name += "."+firstTemplate.getExt();
			}
		}
		return name;
	}
	
	private void createFile(String name) {
		createFile(name, TEMPLATES_DEFAULT);
	}
	
	private void createFile(String name, String template) {
		try {
			String path = originPath(name);
			if (path!=null) {
				String[] v = createIntermediateFolder(path, name);
				path = v[0];
				name = v[1];
				File folder = new File(path);
				DataFolder dfolder = DataFolder.findFolder(FileUtil.toFileObject(folder));
				FileObject firstTemplate = FileUtil.getConfigFile(template);
				if (name.contains(".") && !template.equals(TEMPLATES_DEFAULT)) {
					name = name.substring(0, name.lastIndexOf("."));
				}
				File f = new File(path, name);
				DataObject fo;
				if (!f.exists()) {
					DataObject dTemplate1 = DataObject.find(firstTemplate);
					fo = dTemplate1.createFromTemplate(dfolder, name);
				}
				else {
					FileObject tmp = FileUtil.toFileObject(f);
					fo = DataObject.find(tmp);
				}
				fo.getLookup().lookup(OpenCookie.class).open();
			}
			else {
				JOptionPane.showMessageDialog(null, "La source n'a pas pu être défini", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}
	}
	
	private void createFolder(String name) {
		try {
			String path = originPath(name);
			if (path!=null) {
				String[] v = createIntermediateFolder(path, name);
				path = v[0];
				name = v[1];
				File f = new File(path, name);
				FileUtil.createFolder(f);
			}
		} catch (IOException ex) {
			Exceptions.printStackTrace(ex);
		}
	}
	
	private String originPath(String name) {
		String path;
		if (name.startsWith("/")) {
			Project p = getCurrentProject();
			path = p.getProjectDirectory().getPath();
		}
		else {
			path = getCurrentPath();
		}
		return path;
	}
	
	private String createCompletePath(String path, String name) {
		if (path!=null && name.contains("/")) {
			String[] dec =name.split("/");
			int stop =  dec.length-1;
			if (name.endsWith("/")) stop++;
			for (int i = 0; i < stop;i++) {
				String dec1 = dec[i];
				if (!dec1.isEmpty()) {
					Path p = Paths.get(path, dec1);
					path = p.toString();
				}
			}
		}
		return path;
	}
	
	private String[] createIntermediateFolder(String path, String name) throws IOException {
		if (name.contains("/")) {
			String[] dec =name.split("/");
			for (int i = 0; i < dec.length-1;i++) {
				String dec1 = dec[i];
				if (!dec1.isEmpty()) {
					Path p = Paths.get(path, dec1);
					if (Files.notExists(p)) {
						Files.createDirectories(p);
					}
					path = p.toString();
				}
			}
			name = dec[dec.length-1];
		}
		return new String[]{path, name};
	}
	
	
	private void restoreParams() {
		int nb = Integer.parseInt(NbPreferences.forModule(QuickAssistantPanel.class).get("nbCommand", "-1"));
		data.clear();
		for (int i = 0; i < nb; i++) {
			String nom = NbPreferences.forModule(QuickAssistantPanel.class).get("nom"+i, "");
			String type = NbPreferences.forModule(QuickAssistantPanel.class).get("type"+i, "");
			String prefix = NbPreferences.forModule(QuickAssistantPanel.class).get("prefix"+i, "");
			String argument = NbPreferences.forModule(QuickAssistantPanel.class).get("argument"+i, "");
			String parent = NbPreferences.forModule(QuickAssistantPanel.class).get("parent"+i, "");
			data.add(new String[]{nom, type, prefix, argument, parent});
		}
	}
	
	public String relocateToParentFolder(String path, String parent) {
		File f = new File(path);
		if (f.getName().equals(parent)) {
			return path;
		}
		//Parent Niveau 1 Au dessus
		File pf = f.getParentFile();
		String[] list = pf.list();
		for (String list1 : list) {
			if (list1.equals(parent)) {
				return Paths.get(pf.getPath(), list1).toString();
			}
		}
		//Parent Niveau 2 Au dessus
		File ppf = pf.getParentFile();
		list = ppf.list();
		for (String list1 : list) {
			if (list1.equals(parent)) {
				return Paths.get(ppf.getPath(), list1).toString();
			}
		}
		list = f.list();
		for (String list1 : list) {
			if (list1.equals(parent)) {
				return Paths.get(f.getPath(), list1).toString();
			}
		}
		
		return path;
	}
	
	public String getCurrentPath() {
		
        Lookup lookup = Utilities.actionsGlobalContext();
		for (DataObject dObj : lookup.lookupAll(DataObject.class)) { 
            FileObject fObj = dObj.getPrimaryFile(); 
			String path = fObj.getPath();
			if (path!=null) {
				if (fObj.isFolder()) {
					path = fObj.getPath();
				}
				else {
					path = fObj.getParent().getPath();
				}
				if (selected!=null) {
					path = relocateToParentFolder(path, selected[4]);
				}
				return path;
			}
		}
		
		return null;
	}
	
	public Project getCurrentProject() { 

        Lookup lookup = Utilities.actionsGlobalContext();
		
        for (Project p : lookup.lookupAll(Project.class)) { 
            return p; 
        } 

        for (DataObject dObj : lookup.lookupAll(DataObject.class)) { 
            FileObject fObj = dObj.getPrimaryFile(); 
            Project p = FileOwnerQuery.getOwner(fObj); 
            if (p != null) { 
                return p; 
            } 
        } 

        Project p = OpenProjects.getDefault().getMainProject(); 
        if (p == null) { 
            return p; 
        } 

        for(Project project : OpenProjects.getDefault().getOpenProjects()) { 
            return project; 
        } 

        return null; 
    }
}
