-- Case Study -- 
-- Crime Analysis and Reporting System (C.A.R.S.)

create database cars_db;
use cars_db;

-- 1. Law Enforcement Agencies
create table lawenforcementagencies (
    agencyid int primary key,
    agencyname varchar(100) not null,
    jurisdiction varchar(100),
    contactinfo varchar(150)
);

-- 2. Officers
create table officers (
    officerid int primary key,
    firstname varchar(50),
    lastname varchar(50),
    badgenumber varchar(20),
    ranks varchar(30),
    contactinfo varchar(150),
    agencyid int,
    foreign key (agencyid) references lawenforcementagencies(agencyid)
        on delete cascade
        on update cascade
);

-- 3. Victims
create table victims (
    victimid int primary key,
    firstname varchar(50),
    lastname varchar(50),
    dateofbirth date,
    gender varchar(10),
    contactinfo varchar(150)
);

-- 4. Suspects
create table suspects (
    suspectid int primary key,
    firstname varchar(50),
    lastname varchar(50),
    dateofbirth date,
    gender varchar(10),
    contactinfo varchar(150)
);

-- 5. Incidents
create table incidents (
    incidentid int primary key,
    incidenttype varchar(50),
    incidentdate date,
    locationlatitude decimal(9,6),
    locationlongitude decimal(9,6),
    description text,
    status varchar(30),
    victimid int,
    suspectid int,
    officerid int,
    foreign key (victimid) references victims(victimid)
        on delete set null
        on update cascade,
    foreign key (suspectid) references suspects(suspectid)
        on delete set null
        on update cascade,
    foreign key (officerid) references officers(officerid)
        on delete set null
        on update cascade
);

-- 6. Evidence
create table evidence (
    evidenceid int primary key,
    description text,
    locationfound varchar(100),
    incidentid int,
    foreign key (incidentid) references incidents(incidentid)
        on delete cascade
        on update cascade
);

-- 7. Reports
create table reports (
    reportid int primary key,
    incidentid int,
    reportingofficer int,
    reportdate date,
    reportdetails text,
    status varchar(30),
    foreign key (incidentid) references incidents(incidentid)
        on delete cascade
        on update cascade,
    foreign key (reportingofficer) references officers(officerid)
        on delete set null
        on update cascade
);

-- 1.Law enforcement agencies
insert into lawenforcementagencies values 
(1, 'Chennai City Police', 'Chennai', '123 Mount Road, Chennai'),
(2, 'Coimbatore Commissionerate', 'Coimbatore', '456 Avinashi Road, Coimbatore'),
(3, 'Madurai City Police', 'Madurai', '789 Bypass Road, Madurai'),
(4, 'Salem City Police', 'Salem', '101 Gandhi Road, Salem'),
(5, 'Tiruchirappalli Police', 'Trichy', '202 Cantonment, Tiruchirappalli');

-- 2. Officers
insert into officers values 
(101, 'Arun', 'Kumar', 'CCP101', 'Inspector', 'arun.kumar@tnpolice.gov.in', 1),
(102, 'Revathi', 'S', 'CCB102', 'Sub-Inspector', 'revathi.s@tnpolice.gov.in', 2),
(103, 'Ravi', 'Chandran', 'MCP103', 'Head Constable', 'ravi.c@tnpolice.gov.in', 3),
(104, 'Divya', 'Priya', 'SCP104', 'Inspector', 'divya.p@tnpolice.gov.in', 4),
(105, 'Karthik', 'M', 'TCP105', 'DSP', 'karthik.m@tnpolice.gov.in', 5);

-- 3. Victims
insert into victims values 
(201, 'Meena', 'Lakshmi', '1990-05-20', 'Female', '12 Mylapore St, Chennai'),
(202, 'Saravanan', 'R', '1982-09-15', 'Male', '33 Peelamedu Rd, Coimbatore'),
(203, 'Anitha', 'Raj', '1995-12-05', 'Female', '45 Thirunagar, Madurai'),
(204, 'Vignesh', 'S', '1988-02-28', 'Male', '98 Hasthampatti, Salem'),
(205, 'Priya', 'Kumari', '1993-11-10', 'Female', '65 Srirangam, Trichy');

-- 4. Suspects
insert into suspects values 
(301, 'Mohan', 'Velu', '1975-03-12', 'Male', 'Unknown'),
(302, 'Kala', 'Bharathi', '1985-06-18', 'Female', '221 Vellalore Rd, Coimbatore'),
(303, 'Ramu', 'K', '1991-01-01', 'Male', '56 Karur Main Rd, Trichy'),
(304, 'Subha', 'N', '1980-07-22', 'Female', '89 Arasaradi, Madurai'),
(305, 'Dinesh', 'P', '1978-04-30', 'Male', '112 Alwarpet, Chennai');

-- 5. Incidents
insert into incidents values 
(401, 'Robbery', '2025-03-05', 13.082680, 80.270718, 'Gold robbery at T Nagar showroom', 'Open', 201, 301, 101),
(402, 'Assault', '2025-03-10', 11.016844, 76.955833, 'Assault near Ukkadam Bus Stand', 'Under Investigation', 202, 302, 102),
(403, 'Chain Snatching', '2025-03-12', 9.925201, 78.119775, 'Chain snatching in Simmakkal', 'Closed', 203, 303, 103),
(404, 'Cyber Fraud', '2025-03-15', 11.664325, 78.146014, 'Online banking scam reported', 'Open', 204, 304, 104),
(405, 'Kidnapping', '2025-03-18', 10.790483, 78.704674, 'Minor kidnapped near bus depot', 'Open', 205, 305, 105);

-- 6. Evidence
insert into evidence values 
(501, 'Gold chain recovered', 'Back alley near T Nagar', 401),
(502, 'CCTV footage', 'Shop camera, Ukkadam', 402),
(503, 'Motorbike fragments', 'Simmakkal junction', 403),
(504, 'Bank transaction logs', 'Cyber Cell Division', 404),
(505, 'Child’s backpack', 'Trichy Central Bus Stand', 405);

-- 7. Reports
insert into reports values 
(601, 401, 101, '2025-03-06', 'Store employee identified suspect. Investigation ongoing.', 'Draft'),
(602, 402, 102, '2025-03-11', 'Victim sustained minor injuries. Medical report attached.', 'Finalized'),
(603, 403, 103, '2025-03-13', 'CCTV helped identify bike and plate.', 'Finalized'),
(604, 404, 104, '2025-03-16', 'Fake URL traced to international IP.', 'Draft'),
(605, 405, 105, '2025-03-19', 'Search teams deployed across Trichy.', 'Draft');






