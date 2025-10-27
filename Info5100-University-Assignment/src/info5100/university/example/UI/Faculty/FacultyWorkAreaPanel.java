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
    // Tab 1: My Courses
private javax.swing.JPanel panelMyCourses;
private javax.swing.JComboBox<String> cmbCourseSemester;
private javax.swing.JButton btnRefreshCourses;
private javax.swing.JButton btnUpdateCourseDetails;
private javax.swing.JButton btnCloseCourseEnrollment;
private javax.swing.JButton btnUploadSyllabus;
private javax.swing.JTable tblMyCourses;

// Tab 2: Student Management
private javax.swing.JPanel panelStudentManagement;
private javax.swing.JComboBox<String> cmbStudentCourse;
private javax.swing.JButton btnViewTranscript;
private javax.swing.JButton btnViewProgress;
private javax.swing.JButton btnExportRoster;
private javax.swing.JTable tblEnrolledStudents;

// Tab 3: Grading
private javax.swing.JPanel panelGrading;
private javax.swing.JComboBox<String> cmbGradingCourse;
private javax.swing.JComboBox<String> cmbAssignment;
private javax.swing.JButton btnSaveGrades;
private javax.swing.JButton btnCalculateFinalGrades;
private javax.swing.JButton btnSubmitFinalGrades;
private javax.swing.JTable tblGrading;
private javax.swing.JLabel lblClassAverage;
private javax.swing.JLabel lblClassGPA;

// Tab 4: Performance Reports
private javax.swing.JPanel panelPerformance;
private javax.swing.JComboBox<String> cmbReportSemester;
private javax.swing.JComboBox<String> cmbReportCourse;
private javax.swing.JButton btnGenerateReport;
private javax.swing.JButton btnExportReport;
private javax.swing.JTable tblPerformanceReport;
private javax.swing.JProgressBar progressBarGradeDistribution;
private javax.swing.JLabel lblAverageGrade;
private javax.swing.JLabel lblEnrollmentCount;

// Tab 5: Tuition Insights
private javax.swing.JPanel panelTuitionInsights;
private javax.swing.JComboBox<String> cmbTuitionSemester;
private javax.swing.JButton btnRefreshTuition;
private javax.swing.JTable tblTuitionDetails;
private javax.swing.JLabel lblTotalTuition;
private javax.swing.JLabel lblTotalEnrolled;
private javax.swing.JLabel lblRevenuePerCourse;

// Tab 6: Profile
private javax.swing.JPanel panelProfile;
private javax.swing.JTextField txtFacultyName;
private javax.swing.JTextField txtFacultyId;
private javax.swing.JTextField txtFacultyEmail;
private javax.swing.JTextField txtFacultyPhone;
private javax.swing.JTextField txtFacultyOffice;
private javax.swing.JTextField txtFacultyDepartment;
private javax.swing.JTextField txtOfficeHours;
private javax.swing.JButton btnEditProfile;
private javax.swing.JButton btnSaveProfile;
private javax.swing.JButton btnCancelEdit;
private javax.swing.JButton btnLogout;


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
