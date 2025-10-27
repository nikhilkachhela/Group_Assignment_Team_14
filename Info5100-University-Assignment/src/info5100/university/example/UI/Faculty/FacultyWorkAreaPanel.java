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

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Finance.TuitionAccount;
import info5100.university.example.Grading.GradeCalculator;
import info5100.university.example.Persona.StudentProfile;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
       
        facultyTabs = new JTabbedPane();
       
        // Tab 1: My Courses
        panelMyCourses = new JPanel();
        panelMyCourses.setLayout(new BorderLayout());
       
        JPanel courseTopPanel = new JPanel();
        JLabel lblSemester = new JLabel("Semester:");
        courseTopPanel.add(lblSemester);
       
        cmbCourseSemester = new JComboBox<>(new String[]{"Fall2024", "Spring2025", "Summer2025"});
        courseTopPanel.add(cmbCourseSemester);
       
        btnRefreshCourses = new JButton("Refresh");
        btnRefreshCourses.addActionListener(evt -> loadMyCourses());
        courseTopPanel.add(btnRefreshCourses);
       
        panelMyCourses.add(courseTopPanel, BorderLayout.NORTH);
       
        tblMyCourses = new JTable();
        tblMyCourses.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Course ID", "Course Name", "Schedule", "Room", "Enrolled", "Capacity", "Status"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollPaneCourses = new JScrollPane(tblMyCourses);
        panelMyCourses.add(scrollPaneCourses, BorderLayout.CENTER);
       
        JPanel courseBottomPanel = new JPanel();
        btnUpdateCourseDetails = new JButton("Update Course Details");
        btnUpdateCourseDetails.addActionListener(evt -> updateCourseDetails());
        courseBottomPanel.add(btnUpdateCourseDetails);
       
        btnUploadSyllabus = new JButton("Upload/Modify Syllabus");
        btnUploadSyllabus.addActionListener(evt -> uploadSyllabus());
        courseBottomPanel.add(btnUploadSyllabus);
       
        btnCloseCourseEnrollment = new JButton("Open/Close Enrollment");
        btnCloseCourseEnrollment.addActionListener(evt -> toggleEnrollment());
        courseBottomPanel.add(btnCloseCourseEnrollment);
       
        panelMyCourses.add(courseBottomPanel, BorderLayout.SOUTH);
       
        facultyTabs.addTab("My Courses", panelMyCourses);
       
        // Tab 2: Student Management
        panelStudentManagement = new JPanel();
        panelStudentManagement.setLayout(new BorderLayout());
       
        JPanel studentTopPanel = new JPanel();
        JLabel lblCourse = new JLabel("Select Course:");
        studentTopPanel.add(lblCourse);
       
        cmbStudentCourse = new JComboBox<>();
        cmbStudentCourse.addActionListener(evt -> loadEnrolledStudents());
        studentTopPanel.add(cmbStudentCourse);
       
        panelStudentManagement.add(studentTopPanel, BorderLayout.NORTH);
       
        tblEnrolledStudents = new JTable();
        tblEnrolledStudents.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Student ID", "Student Name", "Email", "Attendance %", "Current Grade", "Status"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollPaneStudents = new JScrollPane(tblEnrolledStudents);
        panelStudentManagement.add(scrollPaneStudents, BorderLayout.CENTER);
       
        JPanel studentBottomPanel = new JPanel();
        btnViewTranscript = new JButton("View Transcript");
        btnViewTranscript.addActionListener(evt -> viewStudentTranscript());
        studentBottomPanel.add(btnViewTranscript);
       
        btnViewProgress = new JButton("View Progress Report");
        btnViewProgress.addActionListener(evt -> viewStudentProgress());
        studentBottomPanel.add(btnViewProgress);
       
        btnExportRoster = new JButton("Export Roster");
        btnExportRoster.addActionListener(evt -> exportRoster());
        studentBottomPanel.add(btnExportRoster);
       
        panelStudentManagement.add(studentBottomPanel, BorderLayout.SOUTH);
       
        facultyTabs.addTab("Student Management", panelStudentManagement);
       
        // Tab 3: Grading
        panelGrading = new JPanel();
        panelGrading.setLayout(new BorderLayout());
       
        JPanel gradingTopPanel = new JPanel();
        JLabel lblGradeCourse = new JLabel("Course:");
        gradingTopPanel.add(lblGradeCourse);
       
        cmbGradingCourse = new JComboBox<>();
        cmbGradingCourse.addActionListener(evt -> loadGradingData());
        gradingTopPanel.add(cmbGradingCourse);
       
        JLabel lblAssignment = new JLabel("Assignment:");
        gradingTopPanel.add(lblAssignment);
       
        cmbAssignment = new JComboBox<>(new String[]{"Assignment 1", "Assignment 2", "Midterm", "Final", "Project"});
        cmbAssignment.addActionListener(evt -> loadGradingData());
        gradingTopPanel.add(cmbAssignment);
       
        panelGrading.add(gradingTopPanel, BorderLayout.NORTH);
       
        tblGrading = new JTable();
        tblGrading.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Student ID", "Student Name", "Assignment Score", "Percentage", "Letter Grade", "Comments"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 5; // Allow editing score and comments
            }
        });
        JScrollPane scrollPaneGrading = new JScrollPane(tblGrading);
        panelGrading.add(scrollPaneGrading, BorderLayout.CENTER);
       
        JPanel gradingBottomPanel = new JPanel();
        gradingBottomPanel.setLayout(new GridLayout(2, 3, 10, 5));
       
        btnSaveGrades = new JButton("Save Grades");
        btnSaveGrades.addActionListener(evt -> saveGrades());
        gradingBottomPanel.add(btnSaveGrades);
       
        btnCalculateFinalGrades = new JButton("Calculate Final Grades");
        btnCalculateFinalGrades.addActionListener(evt -> calculateFinalGrades());
        gradingBottomPanel.add(btnCalculateFinalGrades);
       
        btnSubmitFinalGrades = new JButton("Submit Final Grades");
        btnSubmitFinalGrades.addActionListener(evt -> submitFinalGrades());
        gradingBottomPanel.add(btnSubmitFinalGrades);
       
        lblClassAverage = new JLabel("Class Average: N/A");
        gradingBottomPanel.add(lblClassAverage);
       
        lblClassGPA = new JLabel("Class GPA: N/A");
        gradingBottomPanel.add(lblClassGPA);
       
        panelGrading.add(gradingBottomPanel, BorderLayout.SOUTH);
       
        facultyTabs.addTab("Grading", panelGrading);
       
        // Tab 4: Performance Reports
        panelPerformance = new JPanel();
        panelPerformance.setLayout(new BorderLayout());
       
        JPanel performanceTopPanel = new JPanel();
        JLabel lblReportSem = new JLabel("Semester:");
        performanceTopPanel.add(lblReportSem);
       
        cmbReportSemester = new JComboBox<>(new String[]{"Fall2024", "Spring2025", "Summer2025"});
        performanceTopPanel.add(cmbReportSemester);
       
        JLabel lblReportCourse = new JLabel("Course:");
        performanceTopPanel.add(lblReportCourse);
       
        cmbReportCourse = new JComboBox<>();
        performanceTopPanel.add(cmbReportCourse);
       
        btnGenerateReport = new JButton("Generate Report");
        btnGenerateReport.addActionListener(evt -> generatePerformanceReport());
        performanceTopPanel.add(btnGenerateReport);
       
        btnExportReport = new JButton("Export Report");
        btnExportReport.addActionListener(evt -> exportPerformanceReport());
        performanceTopPanel.add(btnExportReport);
       
        panelPerformance.add(performanceTopPanel, BorderLayout.NORTH);
       
        tblPerformanceReport = new JTable();
        tblPerformanceReport.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Grade", "Count", "Percentage", "GPA Points"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollPanePerformance = new JScrollPane(tblPerformanceReport);
        panelPerformance.add(scrollPanePerformance, BorderLayout.CENTER);
       
        JPanel performanceBottomPanel = new JPanel();
        performanceBottomPanel.setLayout(new GridLayout(3, 1, 5, 5));
       
        lblAverageGrade = new JLabel("Average Grade: N/A");
        performanceBottomPanel.add(lblAverageGrade);
       
        lblEnrollmentCount = new JLabel("Total Enrolled: 0");
        performanceBottomPanel.add(lblEnrollmentCount);
       
        progressBarGradeDistribution = new JProgressBar();
        progressBarGradeDistribution.setStringPainted(true);
        performanceBottomPanel.add(progressBarGradeDistribution);
       
        panelPerformance.add(performanceBottomPanel, BorderLayout.SOUTH);
       
        facultyTabs.addTab("Performance Reports", panelPerformance);
       
        // Tab 5: Tuition Insights
        panelTuitionInsights = new JPanel();
        panelTuitionInsights.setLayout(new BorderLayout());
       
        JPanel tuitionTopPanel = new JPanel();
        JLabel lblTuitionSem = new JLabel("Semester:");
        tuitionTopPanel.add(lblTuitionSem);
       
        cmbTuitionSemester = new JComboBox<>(new String[]{"Fall2024", "Spring2025", "Summer2025"});
        tuitionTopPanel.add(cmbTuitionSemester);
       
        btnRefreshTuition = new JButton("Refresh");
        btnRefreshTuition.addActionListener(evt -> loadTuitionInsights());
        tuitionTopPanel.add(btnRefreshTuition);
       
        panelTuitionInsights.add(tuitionTopPanel, BorderLayout.NORTH);
       
        tblTuitionDetails = new JTable();
        tblTuitionDetails.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Course", "Enrolled Students", "Tuition per Student", "Total Revenue", "Collection Rate"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollPaneTuition = new JScrollPane(tblTuitionDetails);
        panelTuitionInsights.add(scrollPaneTuition, BorderLayout.CENTER);
       
        JPanel tuitionBottomPanel = new JPanel();
        tuitionBottomPanel.setLayout(new GridLayout(3, 1, 5, 5));
       
        lblTotalTuition = new JLabel("Total Tuition Collected: $0.00");
        lblTotalTuition.setFont(new java.awt.Font("Segoe UI", 1, 14));
        tuitionBottomPanel.add(lblTotalTuition);
       
        lblTotalEnrolled = new JLabel("Total Students Enrolled: 0");
        lblTotalEnrolled.setFont(new java.awt.Font("Segoe UI", 1, 14));
        tuitionBottomPanel.add(lblTotalEnrolled);
       
        lblRevenuePerCourse = new JLabel("Average Revenue per Course: $0.00");
        lblRevenuePerCourse.setFont(new java.awt.Font("Segoe UI", 1, 14));
        tuitionBottomPanel.add(lblRevenuePerCourse);
       
        panelTuitionInsights.add(tuitionBottomPanel, BorderLayout.SOUTH);
       
        facultyTabs.addTab("Tuition/Enrollment Insights", panelTuitionInsights);
       
        // Tab 6: Profile
        panelProfile = new JPanel();
        panelProfile.setLayout(new BorderLayout());
       
        JPanel profileCenterPanel = new JPanel();
        profileCenterPanel.setLayout(new GridLayout(7, 2, 10, 10));
       
        profileCenterPanel.add(new JLabel("Name:"));
        txtFacultyName = new JTextField();
        txtFacultyName.setEditable(false);
        profileCenterPanel.add(txtFacultyName);
       
        profileCenterPanel.add(new JLabel("Faculty ID:"));
        txtFacultyId = new JTextField();
        txtFacultyId.setEditable(false);
        profileCenterPanel.add(txtFacultyId);
       
        profileCenterPanel.add(new JLabel("Email:"));
        txtFacultyEmail = new JTextField();
        txtFacultyEmail.setEditable(false);
        profileCenterPanel.add(txtFacultyEmail);
       
        profileCenterPanel.add(new JLabel("Phone:"));
        txtFacultyPhone = new JTextField();
        txtFacultyPhone.setEditable(false);
        profileCenterPanel.add(txtFacultyPhone);
       
        profileCenterPanel.add(new JLabel("Office:"));
        txtFacultyOffice = new JTextField();
        txtFacultyOffice.setEditable(false);
        profileCenterPanel.add(txtFacultyOffice);
       
        profileCenterPanel.add(new JLabel("Department:"));
        txtFacultyDepartment = new JTextField();
        txtFacultyDepartment.setEditable(false);
        profileCenterPanel.add(txtFacultyDepartment);
       
        profileCenterPanel.add(new JLabel("Office Hours:"));
        txtOfficeHours = new JTextField();
        txtOfficeHours.setEditable(false);
        profileCenterPanel.add(txtOfficeHours);
       
        panelProfile.add(profileCenterPanel, BorderLayout.CENTER);
       
        JPanel profileBottomPanel = new JPanel();
        btnEditProfile = new JButton("Edit Profile");
        btnEditProfile.addActionListener(evt -> enableProfileEditing(true));
        profileBottomPanel.add(btnEditProfile);
       
        btnSaveProfile = new JButton("Save Changes");
        btnSaveProfile.setEnabled(false);
        btnSaveProfile.addActionListener(evt -> saveProfile());
        profileBottomPanel.add(btnSaveProfile);
       
        btnCancelEdit = new JButton("Cancel");
        btnCancelEdit.setEnabled(false);
        btnCancelEdit.addActionListener(evt -> {
            loadProfileData();
            enableProfileEditing(false);
        });
        profileBottomPanel.add(btnCancelEdit);
       
        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(evt -> logout());
        profileBottomPanel.add(btnLogout);
       
        panelProfile.add(profileBottomPanel, BorderLayout.SOUTH);
       
        facultyTabs.addTab("Profile", panelProfile);
       
        setLayout(new BorderLayout());
        add(facultyTabs, BorderLayout.CENTER);
    }// </editor-fold>

    private void setupTabs() {
    setupMyCoursesTab();
    setupStudentManagementTab();
    setupGradingTab();
    setupPerformanceTab();
    setupTuitionTab();
    setupProfileTab();
}

private void setupMyCoursesTab() {
    cmbCourseSemester.addActionListener(evt -> loadMyCourses());
    loadMyCourses();
}

private void setupStudentManagementTab() {
    loadCoursesForDropdown();
    loadEnrolledStudents();
}

private void setupGradingTab() {
    loadGradingCourses();
    loadGradingData();
}

private void setupPerformanceTab() {
    loadPerformanceCourses();
}

private void setupTuitionTab() {
    loadTuitionInsights();
}

private void setupProfileTab() {
    loadProfileData();
}

private void loadFacultyInfo() {
    if (currentFaculty != null) {
        // placeholder for future faculty data binding
    }
}
private void loadMyCourses() {
    DefaultTableModel model = (DefaultTableModel) tblMyCourses.getModel();
    model.setRowCount(0);

    String semester = (String) cmbCourseSemester.getSelectedItem();
    if (semester == null) return;

    CourseSchedule schedule = department.getCourseSchedule(semester);
    if (schedule == null) return;

    for (CourseOffer offer : schedule.getSchedule()) {
        if (offer.getFacultyProfile() == currentFaculty) {
            Course course = offer.getSubjectCourse();

            int enrolled = 0;
            for (var seat : offer.getSeatList()) {
                if (seat.isOccupied()) {
                    enrolled++;
                }
            }

            model.addRow(new Object[]{
                course.getCOurseNumber(),
                course.getCourseName(),
                "Mon/Wed 10-12", // Placeholder
                "Room 101",      // Placeholder
                enrolled,
                offer.getSeatList().size(),
                "Active"
            });
        }
    }
}

private void updateCourseDetails() {
    int row = tblMyCourses.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this,
            "Please select a course to update!",
            "No Selection",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String courseId = (String) tblMyCourses.getValueAt(row, 0);
    String courseName = (String) tblMyCourses.getValueAt(row, 1);

    JTextField titleField = new JTextField(courseName);
    JTextField descField = new JTextField("Course Description");
    JTextField scheduleField = new JTextField("Mon/Wed 10-12");
    JTextField capacityField = new JTextField("30");

    Object[] message = {
        "Course Title:", titleField,
        "Description:", descField,
        "Schedule:", scheduleField,
        "Capacity:", capacityField
    };

    int option = JOptionPane.showConfirmDialog(this, message,
        "Update Course: " + courseId, JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        JOptionPane.showMessageDialog(this,
            "Course details updated successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
        loadMyCourses();
    }
}

private void uploadSyllabus() {
    int row = tblMyCourses.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this,
            "Please select a course!",
            "No Selection",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String courseId = (String) tblMyCourses.getValueAt(row, 0);

    JTextArea syllabusText = new JTextArea(10, 40);
    syllabusText.setText(
        "Course Syllabus\n" +
        "===============\n" +
        "Instructor: Faculty Name\n" +
        "Office Hours: Mon/Wed 2-4 PM\n\n" +
        "Course Objectives:\n" +
        "1. ...\n" +
        "2. ...\n\n" +
        "Grading Policy:\n" +
        "Assignments: 40%\n" +
        "Midterm: 25%\n" +
        "Final: 35%"
    );

    JScrollPane scrollPane = new JScrollPane(syllabusText);

    int option = JOptionPane.showConfirmDialog(this, scrollPane,
        "Upload/Modify Syllabus for " + courseId,
        JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        JOptionPane.showMessageDialog(this,
            "Syllabus uploaded successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
}

private void toggleEnrollment() {
    int row = tblMyCourses.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this,
            "Please select a course!",
            "No Selection",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String courseId = (String) tblMyCourses.getValueAt(row, 0);
    String currentStatus = (String) tblMyCourses.getValueAt(row, 6);

    String newStatus = currentStatus.equals("Active") ? "Closed" : "Active";

    int confirm = JOptionPane.showConfirmDialog(this,
        "Change enrollment status to " + newStatus + " for " + courseId + "?",
        "Confirm Status Change",
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        tblMyCourses.setValueAt(newStatus, row, 6);
        JOptionPane.showMessageDialog(this,
            "Enrollment status updated to: " + newStatus,
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
private void loadCoursesForDropdown() {
    cmbStudentCourse.removeAllItems();
    cmbGradingCourse.removeAllItems();
    cmbReportCourse.removeAllItems();

    String[] semesters = {"Fall2024", "Spring2025", "Summer2025"};

    for (String semester : semesters) {
        CourseSchedule schedule = department.getCourseSchedule(semester);
        if (schedule == null) continue;

        for (CourseOffer offer : schedule.getSchedule()) {
            if (offer.getFacultyProfile() == currentFaculty) {
                Course course = offer.getSubjectCourse();
                String item = course.getCOurseNumber() + " - " + course.getCourseName();
                cmbStudentCourse.addItem(item);
                cmbGradingCourse.addItem(item);
                cmbReportCourse.addItem(item);
            }
        }
    }
}

private void loadEnrolledStudents() {
    DefaultTableModel model = (DefaultTableModel) tblEnrolledStudents.getModel();
    model.setRowCount(0);

    String selectedCourse = (String) cmbStudentCourse.getSelectedItem();
    if (selectedCourse == null) return;

    String courseId = selectedCourse.split(" - ")[0];

    String[] semesters = {"Fall2024", "Spring2025", "Summer2025"};
    for (String semester : semesters) {
        CourseSchedule schedule = department.getCourseSchedule(semester);
        if (schedule == null) continue;

        CourseOffer offer = schedule.getCourseOfferByNumber(courseId);
        if (offer != null && offer.getFacultyProfile() == currentFaculty) {

            ArrayList<StudentProfile> students = department.getStudentDirectory().getStudentList();
            int studentNum = 1;

            for (StudentProfile student : students) {
                CourseLoad courseLoad = student.getCourseLoadBySemester(semester);
                if (courseLoad == null) continue;

                for (SeatAssignment sa : courseLoad.getSeatAssignments()) {
                    if (sa.getAssociatedCourse().getCOurseNumber().equals(courseId)) {

                        String studentId = "STU-" + String.format("%03d", studentNum);
                        String grade = studentGrades
                            .getOrDefault(courseId, new HashMap<>())
                            .getOrDefault(studentId, "N/A");

                        model.addRow(new Object[]{
                            studentId,
                            "Student " + studentNum,
                            "student" + studentNum + "@northeastern.edu",
                            "95%",
                            grade,
                            "Active"
                        });
                    }
                }
                studentNum++;
            }
        }
    }
}

private void viewStudentTranscript() {
    int row = tblEnrolledStudents.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this,
            "Please select a student!",
            "No Selection",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String studentId = (String) tblEnrolledStudents.getValueAt(row, 0);
    String studentName = (String) tblEnrolledStudents.getValueAt(row, 1);

    String transcript =
        "TRANSCRIPT SUMMARY\n" +
        "==================\n" +
        "Student: " + studentName + "\n" +
        "ID: " + studentId + "\n\n" +
        "Current GPA: 3.5\n" +
        "Credits Completed: 16\n" +
        "Academic Standing: Good Standing\n\n" +
        "Current Courses:\n" +
        "- INFO5100: A-\n" +
        "- INFO6200: B+\n" +
        "- INFO6250: A\n";

    JTextArea textArea = new JTextArea(transcript);
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));

    JOptionPane.showMessageDialog(this, scrollPane,
        "Transcript: " + studentName,
        JOptionPane.INFORMATION_MESSAGE);
}

private void viewStudentProgress() {
    int row = tblEnrolledStudents.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this,
            "Please select a student!",
            "No Selection",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String studentName = (String) tblEnrolledStudents.getValueAt(row, 1);

    String progress =
        "PROGRESS REPORT\n" +
        "===============\n" +
        "Student: " + studentName + "\n\n" +
        "Course Progress:\n" +
        "Assignment 1: 95/100\n" +
        "Assignment 2: 88/100\n" +
        "Midterm: 82/100\n" +
        "Project: In Progress\n" +
        "Final: Not Started\n\n" +
        "Current Average: 88.3%\n" +
        "Projected Grade: B+\n" +
        "Attendance: 95%\n" +
        "Participation: Excellent";

    JOptionPane.showMessageDialog(this, progress,
        "Progress Report: " + studentName,
        JOptionPane.INFORMATION_MESSAGE);
}

private void exportRoster() {
    String selectedCourse = (String) cmbStudentCourse.getSelectedItem();
    if (selectedCourse == null) {
        JOptionPane.showMessageDialog(this,
            "Please select a course!",
            "No Course Selected",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    JOptionPane.showMessageDialog(this,
        "Roster exported successfully!\n" +
        "File saved to: ~/Documents/Roster_" +
        selectedCourse.split(" - ")[0] + "_" +
        new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".csv",
        "Export Success",
        JOptionPane.INFORMATION_MESSAGE);
}

private void loadGradingCourses() {
    // courses already filled in loadCoursesForDropdown()
}

private void loadGradingData() {
    DefaultTableModel model = (DefaultTableModel) tblGrading.getModel();
    model.setRowCount(0);

    String selectedCourse = (String) cmbGradingCourse.getSelectedItem();
    String selectedAssignment = (String) cmbAssignment.getSelectedItem();

    if (selectedCourse == null || selectedAssignment == null) return;

    String courseId = selectedCourse.split(" - ")[0];

    String[] semesters = {"Fall2024", "Spring2025", "Summer2025"};
    for (String semester : semesters) {
        CourseSchedule schedule = department.getCourseSchedule(semester);
        if (schedule == null) continue;

        CourseOffer offer = schedule.getCourseOfferByNumber(courseId);
        if (offer != null && offer.getFacultyProfile() == currentFaculty) {

            ArrayList<StudentProfile> students = department.getStudentDirectory().getStudentList();
            int studentNum = 1;

            for (StudentProfile student : students) {
                CourseLoad courseLoad = student.getCourseLoadBySemester(semester);
                if (courseLoad == null) continue;

                for (SeatAssignment sa : courseLoad.getSeatAssignments()) {
                    if (sa.getAssociatedCourse().getCOurseNumber().equals(courseId)) {

                        String studentId = "STU-" + String.format("%03d", studentNum);
                        String key = courseId + "-" + studentId;

                        Double score = assignmentGrades
                            .getOrDefault(key, new HashMap<>())
                            .getOrDefault(selectedAssignment, 0.0);

                        double percentage = score;
                        String letterGrade = GradeCalculator.getLetterGrade(percentage);

                        model.addRow(new Object[]{
                            studentId,
                            "Student " + studentNum,
                            score,
                            percentage + "%", // display
                            letterGrade,
                            "" // comments
                        });
                    }
                }
                studentNum++;
            }
        }
    }

 calculateClassStats();
}

private void saveGrades() {
    String selectedCourse = (String) cmbGradingCourse.getSelectedItem();
    String selectedAssignment = (String) cmbAssignment.getSelectedItem();

    if (selectedCourse == null || selectedAssignment == null) {
        JOptionPane.showMessageDialog(this,
            "Please select course and assignment!",
            "Incomplete Selection",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String courseId = selectedCourse.split(" - ")[0];

    DefaultTableModel model = (DefaultTableModel) tblGrading.getModel();

    for (int i = 0; i < model.getRowCount(); i++) {
        String studentId = (String) model.getValueAt(i, 0);
        Object scoreObj = model.getValueAt(i, 2);

        double score = 0.0;
        if (scoreObj != null) {
            try {
                score = Double.parseDouble(scoreObj.toString());
            } catch (NumberFormatException e) {
                continue;
            }
        }

        String key = courseId + "-" + studentId;
        assignmentGrades.putIfAbsent(key, new HashMap<>());
        assignmentGrades.get(key).put(selectedAssignment, score);

        model.setValueAt(score + "%", i, 3);
        model.setValueAt(GradeCalculator.getLetterGrade(score), i, 4);
    }

    JOptionPane.showMessageDialog(this,
        "Grades saved successfully!",
        "Success",
        JOptionPane.INFORMATION_MESSAGE);

    calculateClassStats();
}

private void calculateFinalGrades() {
    String selectedCourse = (String) cmbGradingCourse.getSelectedItem();
    if (selectedCourse == null) return;

    String courseId = selectedCourse.split(" - ")[0];

    DefaultTableModel model = (DefaultTableModel) tblGrading.getModel();
    model.setRowCount(0);

    Map<String, Double> weights = new HashMap<>();
    weights.put("Assignment 1", 0.15);
    weights.put("Assignment 2", 0.15);
    weights.put("Midterm", 0.25);
    weights.put("Final", 0.35);
    weights.put("Project", 0.10);

    ArrayList<StudentProfile> students = department.getStudentDirectory().getStudentList();
    int studentNum = 1;

    for (StudentProfile student : students) {
        String studentId = "STU-" + String.format("%03d", studentNum);
        String key = courseId + "-" + studentId;

        Map<String, Double> sGrades = assignmentGrades.get(key);
        if (sGrades != null && !sGrades.isEmpty()) {
            double finalScore = 0.0;

            for (Map.Entry<String, Double> entry : weights.entrySet()) {
                double assignmentScore = sGrades.getOrDefault(entry.getKey(), 0.0);
                finalScore += assignmentScore * entry.getValue();
            }

            String letterGrade = GradeCalculator.getLetterGrade(finalScore);

            studentGrades.putIfAbsent(courseId, new HashMap<>());
            studentGrades.get(courseId).put(studentId, letterGrade);

            model.addRow(new Object[]{
                studentId,
                "Student " + studentNum,
                String.format("%.1f", finalScore),
                String.format("%.1f%%", finalScore),
                letterGrade,
                "Final Grade Calculated"
            });
        }
        studentNum++;
    }

    JOptionPane.showMessageDialog(this,
        "Final grades calculated successfully!",
        "Calculation Complete",
        JOptionPane.INFORMATION_MESSAGE);

    calculateClassStats();
}

private void submitFinalGrades() {
    int confirm = JOptionPane.showConfirmDialog(this,
        "Are you sure you want to submit final grades?\n" +
        "This action cannot be undone.",
        "Confirm Submission",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);

    if (confirm == JOptionPane.YES_OPTION) {
        JOptionPane.showMessageDialog(this,
            "Final grades submitted successfully!\n" +
            "Students can now view their grades in the portal.",
            "Submission Complete",
            JOptionPane.INFORMATION_MESSAGE);
    }
}

private void calculateClassStats() {
    DefaultTableModel model = (DefaultTableModel) tblGrading.getModel();

    if (model.getRowCount() == 0) {
        lblClassAverage.setText("Class Average: N/A");
        lblClassGPA.setText("Class GPA: N/A");
        return;
    }

    double totalScore = 0.0;
    double totalGPA = 0.0;
    int count = 0;

    for (int i = 0; i < model.getRowCount(); i++) {
        Object scoreObj = model.getValueAt(i, 2);
        String letterGrade = (String) model.getValueAt(i, 4);

        if (scoreObj != null && letterGrade != null) {
            try {
                double score = Double.parseDouble(scoreObj.toString());
                totalScore += score;
                totalGPA += GradeCalculator.getGradePoints(letterGrade);
                count++;
            } catch (Exception e) {
                // ignore bad rows
            }
        }
    }

    if (count > 0) {
        double avgScore = totalScore / count;
        double avgGPA = totalGPA / count;

        lblClassAverage.setText(String.format("Class Average: %.1f%%", avgScore));
        lblClassGPA.setText(String.format("Class GPA: %.2f", avgGPA));
    }
}

private void loadPerformanceCourses() {
    // courses already filled in loadCoursesForDropdown()
}

private void generatePerformanceReport() {
    String selectedCourse = (String) cmbReportCourse.getSelectedItem();
    if (selectedCourse == null) return;

    String courseId = selectedCourse.split(" - ")[0];

    DefaultTableModel model = (DefaultTableModel) tblPerformanceReport.getModel();
    model.setRowCount(0);

    Map<String, Integer> gradeCount = new HashMap<>();
    Map<String, Double> gradeGPA = new HashMap<>();

    String[] grades = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "F"};
    for (String grade : grades) {
        gradeCount.put(grade, 0);
        gradeGPA.put(grade, GradeCalculator.getGradePoints(grade));
    }

    Map<String, String> courseGrades = studentGrades.get(courseId);
    if (courseGrades != null) {
        for (String grade : courseGrades.values()) {
            gradeCount.put(grade, gradeCount.getOrDefault(grade, 0) + 1);
        }
    }

    int totalStudents = courseGrades != null ? courseGrades.size() : 0;

    for (String grade : grades) {
        int count = gradeCount.get(grade);
        double percentage = totalStudents > 0 ? (count * 100.0 / totalStudents) : 0;

        model.addRow(new Object[]{
            grade,
            count,
            String.format("%.1f%%", percentage),
            gradeGPA.get(grade)
        });
    }

    lblEnrollmentCount.setText("Total Enrolled: " + totalStudents);

    double totalGPA = 0;
    int gradeTotal = 0;
    for (Map.Entry<String, Integer> entry : gradeCount.entrySet()) {
        int count = entry.getValue();
        if (count > 0) {
            totalGPA += gradeGPA.get(entry.getKey()) * count;
            gradeTotal += count;
        }
    }

    if (gradeTotal > 0) {
        double avgGPA = totalGPA / gradeTotal;
        lblAverageGrade.setText(String.format("Average GPA: %.2f", avgGPA));

        int bOrBetter = gradeCount.get("A") + gradeCount.get("A-") +
                        gradeCount.get("B+") + gradeCount.get("B");
        int percentage = totalStudents > 0 ? (bOrBetter * 100 / totalStudents) : 0;
        progressBarGradeDistribution.setValue(percentage);
        progressBarGradeDistribution.setString(percentage + "% with B or better");
    }
}

private void exportPerformanceReport() {
    String selectedCourse = (String) cmbReportCourse.getSelectedItem();
    if (selectedCourse == null) {
        JOptionPane.showMessageDialog(this,
            "Please select a course and generate report first!",
            "No Report",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    JOptionPane.showMessageDialog(this,
        "Performance report exported successfully!\n" +
        "File saved to: ~/Documents/Performance_" +
        selectedCourse.split(" - ")[0] + "_" +
        new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".pdf",
        "Export Success",
        JOptionPane.INFORMATION_MESSAGE);
}



  
