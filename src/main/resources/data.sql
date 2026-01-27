-- INSERT INTO patient (name, gender, birth_date, email, blood_group)
-- VALUES
--     ('Aarav Sharma', 'MALE', '1990-05-10', 'aarav.sharma@example.com', 'O_POSITIVE'),
--     ('Diya Patel', 'FEMALE', '1995-08-20', 'diya.patel@example.com', 'A_POSITIVE'),
--     ('Dishant Verma', 'MALE', '1988-03-15', 'dishant.verma@example.com', 'A_POSITIVE'),
--     ('Neha Iyer', 'FEMALE', '1992-12-01', 'neha.iyer@example.com', 'AB_POSITIVE'),
--     ('Kabir Singh', 'MALE', '1993-07-11', 'kabir.singh@example.com', 'O_POSITIVE');

-- INSERT INTO doctor (name, specialization, email)
-- VALUES
--     ('Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@example.com'),
--     ('Dr. Sneha Kapoor', 'Dermatology', 'sneha.kapoor@example.com'),
--     ('Dr. Arjun Nair', 'Orthopedics', 'arjun.nair@example.com');
-- INSERT INTO patient (name, gender, birth_date, email, blood_group)
-- VALUES
--     ('Rohan Mehta', 'MALE', '1991-02-18', 'rohan.mehta@example.com', 'B_POSITIVE'),
--     ('Ananya Singh', 'FEMALE', '1996-11-05', 'ananya.singh@example.com', 'O_POSITIVE'),
--     ('Vikram Rao', 'MALE', '1987-09-22', 'vikram.rao@example.com', 'AB_POSITIVE'),
--     ('Pooja Nair', 'FEMALE', '1994-01-14', 'pooja.nair@example.com', 'A_POSITIVE'),
--     ('Sandeep Kumar', 'MALE', '1989-06-30', 'sandeep.kumar@example.com', 'B_POSITIVE'),
--     ('Kavya Reddy', 'FEMALE', '1997-03-09', 'kavya.reddy@example.com', 'O_POSITIVE'),
--     ('Amit Joshi', 'MALE', '1990-12-25', 'amit.joshi@example.com', 'A_POSITIVE'),
--     ('Sneha Banerjee', 'FEMALE', '1993-04-17', 'sneha.banerjee@example.com', 'AB_POSITIVE'),
--     ('Rahul Malhotra', 'MALE', '1988-08-08', 'rahul.malhotra@example.com', 'O_POSITIVE'),
--     ('Nidhi Gupta', 'FEMALE', '1995-10-21', 'nidhi.gupta@example.com', 'B_POSITIVE'),
--     ('Arjun Kapoor', 'MALE', '1992-05-13', 'arjun.kapoor@example.com', 'A_POSITIVE'),
--     ('Meera Iyer', 'FEMALE', '1986-07-02', 'meera.iyer@example.com', 'O_POSITIVE'),
--     ('Kunal Shah', 'MALE', '1994-09-19', 'kunal.shah@example.com', 'AB_POSITIVE'),
--     ('Ritika Chawla', 'FEMALE', '1998-02-27', 'ritika.chawla@example.com', 'A_POSITIVE'),
--     ('Deepak Verma', 'MALE', '1985-11-11', 'deepak.verma@example.com', 'B_POSITIVE');
INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
    ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
    ('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
    ('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
    ('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
    ('2025-07-05 16:15:00', 'Consultation', 1, 4),
    ('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5);