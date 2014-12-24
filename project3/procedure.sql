set serveroutput on size 32000


--Procedure to print department, number of students and name of the students
create or replace procedure pro_department_report as

count number;
CURSOR stu_cur is select deptid, dname from Department ORDER BY dname ASC;
CURSOR new_cur is Select d.deptid, count(s.deptid) as cnt FROM DEPARTMENT d LEFT JOIN student s ON s.deptid = d.deptid GROUP BY d.deptid;
CURSOR name_cur is Select s.deptid, s.sname as name FROM student s, department d WHERE s.deptid = d.deptid ORDER BY s.sname ASC;

stu_rec stu_cur%rowtype;
new_rec new_cur%rowtype;
name_rec name_cur%rowtype;


a integer := 0;
begin

	dbms_output.put_line('pro_department_report:' || chr(10));
	for stu_rec in stu_cur loop
		dbms_output.put_line('Department: ' || stu_rec.dname);
		for new_rec in new_cur loop
			if new_rec.deptid = stu_rec.deptid THEN
				dbms_output.put_line('Total number of students: ' || new_rec.cnt);
				dbms_output.put_line('-------------');
			end if;
		end loop;
		for name_rec in name_cur loop
			if name_rec.deptid = stu_rec.deptid THEN
				a := a+1;
				dbms_output.put_line(a || '. ' || name_rec.name);
			end if;
		end loop;
	dbms_output.put_line(chr(13));
	a := 0;
	end loop;
end pro_department_report;
/

--actually run the procedure
begin
pro_department_report;
end;
/


--procedure to print student name and number of classes
create or replace procedure pro_student_stats as

CURSOR stu_cur is select s.sname as name, count(e.snum) as snum FROM enrolled e LEFT JOIN student s on s.snum=e.snum GROUP BY s.sname ORDER BY s.sname;
stu_rec stu_cur%rowtype;
a integer := 0;
temp varchar2(10) := 'hello';
BEGIN
	dbms_output.put_line('pro_student_stats: ' || chr(10));
	dbms_output.put_line('Student Name' || '  ' || '# Classes');
	dbms_output.put_line('------------' || '  ' || '---------');
	for stu_rec in stu_cur loop
		a := length(stu_rec.name);
		a := 12 - a+2;
		dbms_output.put_line(stu_rec.name || rpad(' ',a,' ') || stu_rec.snum);
	end loop;
end pro_student_stats;
/

begin
pro_student_stats;
end;
/



--Procedure to print histogram of number of classes per faculty

create or replace procedure pro_faculty_stats as

CURSOR stu_cur is select f.fname as name, count(e.cname) as temp FROM faculty f LEFT JOIN class c ON f.fid = c.fid LEFT JOIN enrolled e ON c.cname = e.cname GROUP BY f.fname ORDER BY f.fname;

stu_rec stu_cur%rowtype;
minval integer;
maxval integer;
value integer;
checkval integer;
temp varchar2(20);
countval integer;
len integer;

BEGIN
	dbms_output.put_line('pro_faculty_stats: ' || chr(10));
	for stu_rec in stu_cur loop
		value := stu_rec.temp;
	end loop;
	for stu_rec in stu_cur loop
		if value < stu_rec.temp THEN
			value := stu_rec.temp;
		end if;
	end loop;
	maxval := value;
	for stu_rec in stu_cur loop
		if value > stu_rec.temp THEN
			value := stu_rec.temp;
		end if;
	end loop;
	minval := value;

	if mod((minval+maxval),4) = 0 THEN
		value := minval+maxval/4;
	elsif mod((minval+maxval),4) = 2 THEN
		value := ((minval+maxval)/4)-2;
	else
		value := ((minval+maxval)/4)-1;
	end if;

	checkval := (minval+value+value+value+value);

	if checkval = maxval THEN
		dbms_output.put_line('Faculty name   #Students:  ' || minval || '      ' || '>' || minval || ',<=' || (minval+value) || ' >' || (minval+value) || ',<=' || (minval+value+value) || ' >' || (minval+value+value) || ',<=' || (minval+value+value+value) ||  ' >' || (minval+value+value+value) || ',<=' || (minval+value+value+value+value) ); 
		dbms_output.put_line('------------               ' || '------ ------ ------ ------ ------');
	else 
		dbms_output.put_line('Faculty name   #Students:  ' || minval || '      ' || '>' || minval || ',<=' || (minval+value) || ' >' || (minval+value) || ',<=' || (minval+value+value) || ' >' || (minval+value+value) || ',<=' || (minval+value+value+value) ||  ' >' || (minval+value+value+value) || ',<=' || (minval+value+value+value+value) || ' >' || (minval+value+value+value+value)); 
		dbms_output.put_line('------------               ' || '------ ------ ------ ------ ------  ------');
	end if;
	
	for stu_rec in stu_cur loop
		countval := stu_rec.temp;
		if countval = minval THEN
			len := 29 - length(stu_rec.name);
			dbms_output.put_line(stu_rec.name || rpad(' ',len,' ')|| 'x');
		elsif countval > minval and countval <= minval+value THEN
			len := 36 - length(stu_rec.name);
			dbms_output.put_line(stu_rec.name || rpad(' ',len,' ')|| 'x');
		elsif countval > (minval+value) and countval <= (minval+value+value) THEN
			len := 43 - length(stu_rec.name);
			dbms_output.put_line(stu_rec.name || rpad(' ',len,' ')|| 'x');
		elsif countval > (minval+value+value) and countval <= (minval+value+value+value) THEN
			len := 49 - length(stu_rec.name);
			dbms_output.put_line(stu_rec.name || rpad(' ',len,' ')|| 'x');
		elsif countval > (minval+value+value+value) and countval <= (minval+value+value+value+value) THEN
			len := 57 - length(stu_rec.name);
			dbms_output.put_line(stu_rec.name || rpad(' ',len,' ')|| 'x');
		else
			len := 64 - length(stu_rec.name);
			dbms_output.put_line(stu_rec.name || rpad(' ',len,' ')|| 'x');
		end if;
	end loop;
			
end pro_faculty_stats;
/

begin
pro_faculty_stats;
end;
/

--Procedure to print age and number of student in that age group
create or replace procedure pro_department_report as

CURSOR stu_cur is select min(age) as minval, max(age) as maxval, median(age) as med FROM student;
CURSOR age_cur is select age, count(age) as cage FROM student GROUP BY age ORDER BY age;

stu_rec stu_cur%rowtype;
age_rec age_cur%rowtype;

mage integer;
maxage integer;
medage integer;
i integer;
countval integer;
counter integer;

BEGIN
	for stu_rec in stu_cur loop
		mage := stu_rec.minval;
		maxage := stu_rec.maxval;
		medage := stu_rec.med;
	end loop;
	countval := 0;
	
	for age_rec in age_cur loop
		countval := countval + 1;
	end loop;

	counter := 0;
	dbms_output.put_line('pro_histogram: ' || chr(10));
	dbms_output.put_line('Age | number of students');
	for i in mage..maxage loop
		for age_rec in age_cur loop
			if i = age_rec.age THEN
				if i = medage THEN
					dbms_output.put_line(i || '  |  ' || age_rec.cage || ' <-- median');
				else
					dbms_output.put_line(i || '  |  ' || age_rec.cage);
				end if;
			else
				counter := counter + 1;
				
			end if;
		end loop;
		if counter = countval THEN
			dbms_output.put_line(i || '  |  0');
		end if;
		counter := 0;
	end loop;

end pro_department_report;
/

begin
pro_department_report;
end;
/


create or replace procedure pro_enroll_report(num IN integer, name IN varchar2) as

CURSOR stu_cur is select snum, cname from enrolled ORDER BY snum ASC;

stu_rec stu_cur%rowtype;

BEGIN
	dbms_output.put_line('snum       cname');
	dbms_output.put_line('---------- --------------------');

	for stu_rec in stu_cur loop
		dbms_output.put_line('      '||stu_rec.snum || '     ' || stu_rec.cname);
	end loop;
	INSERT INTO enrolled(snum, cname) values (num, name);

	dbms_output.put_line(chr(13));
	dbms_output.put_line('snum       cname');
	dbms_output.put_line('---------- --------------------');
	for stu_rec in stu_cur loop
		dbms_output.put_line('      '||stu_rec.snum || '     ' || stu_rec.cname);
	end loop;


end pro_enroll_report;
/

begin
pro_enroll_report(671,'ENG320');
pro_enroll_report(3726, 'CS448');
end;
/









