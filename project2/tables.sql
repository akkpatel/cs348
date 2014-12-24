

CREATE TABLE STUDENT(
	snum integer NOT NULL,
	sname VARCHAR(30),
	deptid integer,
	slevel VARCHAR(30),
	age integer,
	PRIMARY KEY(snum)
);

CREATE TABLE CLASS(
	cname VARCHAR(30) NOT NULL,
	meets_at DATE,
	room VARCHAR(30),
	fid integer,
	PRIMARY KEY(cname)
);

CREATE TABLE ENROLLED(
	snum VARCHAR(30) NOT NULL,
	cname VARCHAR(30) NOT NULL,
	PRIMARY KEY(snum, cname)
);

CREATE TABLE FACULTY(
	fid integer NOT NULL,
	fname VARCHAR(30),
	deptid integer,
	PRIMARY KEY(fid)
);

CREATE TABLE DEPARTMENT(
	deptid integer NOT NULL,
	dname VARCHAR(30),
	location VARCHAR(30),
	PRIMARY KEY(deptid)
);