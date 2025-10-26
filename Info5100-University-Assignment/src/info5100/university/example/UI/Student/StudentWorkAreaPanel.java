/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package info5100.university.example.UI.Student;

import info5100.university.example.AccessControl.AuthenticationService;
import info5100.university.example.AccessControl.User;
import info5100.university.example.AccessControl.UserDirectory;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.Seat;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Department.Department;
import info5100.university.example.Finance.FinanceManager;
import info5100.university.example.Finance.TuitionAccount;
import info5100.university.example.Grading.GradeCalculator;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.StudentProfile;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 * Student Work Area Panel
 * @author Student Module Developer Nikhil
 */
public class StudentWorkAreaPanel extends javax.swing.JPanel {
    
    private Department department;
    private UserDirectory userDirectory;
    private FinanceManager financeManager;
    private JPanel cardPanel;
    private AuthenticationService authService;
    private StudentProfile currentStudent;
    
    // Constants for MSIS Program
    private static final int REQUIRED_CREDIT_HOURS = 32;
    private static final int MAX_CREDITS_PER_SEMESTER = 8;
    private static final String CORE_COURSE = "INFO5100";
    private static final int CORE_COURSE_CREDITS = 4;