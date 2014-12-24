
Select d.deptid, count(s.deptid)
FROM DEPARTMENT d
LEFT JOIN student s
ON s.deptid = d.deptid
GROUP BY d.deptid
ORDER BY deptid ASC;


Select s.deptid, s.sname
FROM student s, Department d
WHERE s.deptid = d.deptid
ORDER BY s.sname ASC;


select s.sname, count(e.snum)
FROM enrolled e
LEFT JOIN student s
on s.snum = e.snum
GROUP BY s.sname
ORDER BY s.sname;

Select f.fname, count(e.cname)
FROM faculty f, enrolled e, class c
WHERE f.fid = c.fid AND c.cname = e.cname
GROUP BY f.fname;



select f.fname, count(e.cname) as temp
FROM faculty f
LEFT JOIN class c
ON f.fid = c.fid
LEFT JOIN enrolled e
ON c.cname = e.cname
GROUP BY f.fname
ORDER BY f.fname;


select age, count(age)
FROM student
GROUP BY age
ORDER BY age;

select min(age), max(age), median(age)
from student;


select median(age)
from student;

select snum, cname
from enrolled
ORDER BY snum;


