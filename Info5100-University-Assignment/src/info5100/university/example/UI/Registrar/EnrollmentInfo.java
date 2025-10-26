/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.UI.Registrar;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.Seat;
import info5100.university.example.Persona.StudentProfile;


/**
 *
 * @author dives
 */
public class EnrollmentInfo {
    public StudentProfile student;
    public CourseOffer offer;
    public Seat seat;
    public String semester;
    
    public EnrollmentInfo(StudentProfile student, CourseOffer offer, 
                         Seat seat, String semester) {
        this.student = student;
        this.offer = offer;
        this.seat = seat;
        this.semester = semester;
    }
}