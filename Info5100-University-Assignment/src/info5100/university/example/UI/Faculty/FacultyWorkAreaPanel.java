package info5100.university.example.UI.Faculty;

import info5100.university.example.AccessControl.AuthenticationService;
import info5100.university.example.AccessControl.UserDirectory;
import info5100.university.example.Department.Department;
import info5100.university.example.Finance.FinanceManager;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Faculty Work Area Panel
 * @author Faculty Module Developer
 */
public class FacultyWorkAreaPanel extends javax.swing.JPanel {

    private Department department;
    private UserDirectory userDirectory;
    private FinanceManager financeManager;
    private JPanel cardPanel;
    private AuthenticationService authService;
    private FacultyProfile currentFaculty;

    // grade caches
    private Map<String, Map<String, String>> studentGrades = new HashMap<>();
    private Map<String, Map<String, Double>> assignmentGrades = new HashMap<>();

    // top-level tabs
    private JTabbedPane facultyTabs;

    public FacultyWorkAreaPanel(Department dept,
                                UserDirectory userDir,
                                FinanceManager finMgr,
                                JPanel parentCardPanel,
                                AuthenticationService auth,
                                FacultyProfile faculty) {

        this.department = dept;
        this.userDirectory = userDir;
        this.financeManager = finMgr;
        this.cardPanel = parentCardPanel;
        this.authService = auth;
        this.currentFaculty = faculty;

        initComponents();
        setupTabs();
        loadFacultyInfo();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // will build UI in later commits
        facultyTabs = new JTabbedPane();
        setLayout(new java.awt.BorderLayout());
        add(facultyTabs, java.awt.BorderLayout.CENTER);
    }

    private void setupTabs() {
        // to be filled
    }

    private void loadFacultyInfo() {
        // to be filled
    }
}
