# MavAppoint
##Online Academic Advising System for University of Texas at Arlington##

The Academic Advising System is designed for departments across the University of Texas at Arlington to provide a flexible, user-friendly and efficient advising appointment system to assist the students and advisors. The Academic Advising System provides an interface for the students and the advisors to schedule advising sessions and eliminate the necessity for the students to stand in long queues. The system provides placing new appointment, modifying and deleting an existing appointment and screening the synchronized calendar.

###Scope###

The scope of the project can be summarized as follows: 

•	To provide system administrator the access to create login accounts for advisors and managing the schedules, if needed.

•	To provide an interface for students to login using their student ID or as guest user.

•	To prepare suitable schedule for the advisors. 

•	To present a good user interface for creating, deferring, cancelling, editing, and updating the appointment schedule.

•	To help students get an appointment with applicable and available advisors.

•	To report concisely the advisor-student relationship by keeping a record of every required detail of every appointment that takes (will take) place between them. 


###Specific Requirements###

**Functional Requirements**

R1: The system is accessible by three types of users – Administrator, Advisors and students. 

R2: The administrator can create database for their department using department ID.

R3: The administrator is responsible for creating, activating, deactivating and deleting login accounts for the advisors.
  R3.1: The administrator creates advisors’ login account using advisor’s email ID (username), first name, last name and contact number. 
  R3.2: The administrator creates an initial password for the advisor.
  R3.3: The administrator should be able to activate and deactivate an advisor’s account for a specific period of time.
  R3.4: The administrator should be able to delete an advisor’s account.

R4: The advisor should be able to schedule advising sessions with students.
  R4.1: The advisor should be able to login to the system using his email ID as username and the initial password provided by the administrator.
  R4.2: The advisor should be able to change his username, password, profile photo and contact number. 
  R4.3: The advisor can create a task (advising agenda) by providing the task name and having a pre-defined time duration of 10 minutes.
    R4.3.1: The advisor can over-write the time duration for each task.
    R4.3.2: The advisor can edit and delete the task.
  R4.4: The advisor can define the advising hours for each week by providing the days, start time and end time.
    R4.4.1: The system should allow the advisor to repeat the same advising hours for multiple weeks.
    R4.4.2: The advisor can add, delete or edit the defined advising hours.
  R4.5: The advisor should be able to update or delete an appointment with a student.
    R4.5.1: The student should receive an email notification if any modifications are made to his/her appointment by the advisor.
  R4.6: the advisor should be displayed the student name, ID and reason for advising appointment.
  R.4.7: The advisor receives an email notification one day prior to the scheduled appointments. 
    R.4.7.1: The email notification provides a table displaying student name, ID and reason for the advising appointment for each student.
    R.4.7.2: The advisor should be able to turn on/off the email notification feature.

R5: A student should be able to register and login to schedule an appointment.
  R5.1: The student can register by providing name, Student Id, MyMav email address and contact number.
    R5.1.1: The student can login using student Id and password 
  R5.2: The student can also schedule an appointment as a guest user by providing his email address.
    R5.2.1: The system should send a link to the email address for verification.   

R6: The student should be able to schedule an appointment with available advisors.
  R6.1: The system should ask the student to select the department and degree level.
  R6.2: The system displays a detailed list of previous and future advising sessions to the student. The details included are Advisor name, date, time and task.
  R6.3: The system allots a default advisor to the student based on the first letter of the student’s last name.
  R6.4: The student can change the default advisor with any available advisor provided by the system.
  R6.5: The student can select any available time slot that is returned by the system for a particular advisor.
  R6.6: The student should be able to select a particular task for the appointment and enter a short description (optional).
  R6.7: The systems should send a confirmation email to the student’s email address after setting up an appointment. 
    R6.7.1: The confirmation email contains details such as Advisor name, advising date, time and task.

R7: The student should be able to cancel an appointment.
  R7.1: If the student has set an appointment as a guest user, the system should cancel the appointment by sending an email to the student.

R8: The system should synchronize the outlook calendar for both advisor and student whenever an appointment is created, edited or canceled.

R9: The system should allow the students to give feedbacks about the website through a form.
  R9.1: The administrator should have access to all the feedbacks.

R10:  The system should allow the advisors and students to report any bug in the system.
  R10.1: The administrator should have access to all the bugs reported.

R11: The system provides Help manuals for the users.
  R11.1: The system should provide hyper-link to the specific section of help manual for a particular task on the website.

##Non-functional requirements##

R1: The system should deploy easily in a user-friendly manner without any technical effort.

R2: The system should be highly secure against attacks.

  R2.1: The Student IDs should be encrypted while storing in database. 
  
  R2.2: The passwords should be hashed while storing in database.
  
R3: The system should have the similar look and feel of http://www.uta.edu.

R4: The system should be easily scalable and adaptable to different Database Management Systems.  

R5: The system should be well documented. The documents should be updated whenever the system encounters a change.



**Abstract Use Cases**

UC1: Create Advisor Account (Actor: Admin, System: Academic Advising System)
 
UC2: Delete Advisor Account (Actor: Admin, System: Academic Advising System)
 
UC3: Allocate Students to Advisor (Actor: Admin, System: Academic Advising System) 

UC4: Create Student Account (Actor: UTA student: Academic Advising System) 

UC5: Log in (Actor: Account holder user, System: Academic Advising System) 

UC6: Log Out (Actor: Account holder user, System: Academic Advising System) 

UC7: Reset Password (Actor: Account holder user, System: Academic Advising System) 

UC8: Schedule an Appointment (Actor: Student, System: Academic Advising System) 

UC9: Cancel an Appointment (Actor: Student and Advisor, System: Academic Advising System) 

UC10: Turn Off Notification (Actor: Student and Advisor, System: Academic Advising System) 

UC11: View Appointment (Actor: Student, System: Academic Advising System) 

UC12: Set Advisor Hours (Actor: Advisor, System: Academic Advising System) 

UC13: Edit Advisor Hours (Actor: Advisor, System: Academic Advising System) 

UC14: Write Feedback (Actor: Users, System: Academic Advising System) 


Skeleton Code provided by Dr. David Kung (Software Engineering Director, UT Arlington)

Team members: Rajat Dhanuka, Ankita Bhargave, Akilesh Rajan, Milind Bhattacharya and Sangram Bankar

