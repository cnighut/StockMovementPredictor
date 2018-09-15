import csv
import json
import ftplib

csvfile = open('prediction.csv', 'r')
jsonfile = open('predictionf.json', 'w')

csvfile2 = open('data.csv', 'r')
jsonfile2 = open('dataf.json', 'w')

fieldnames = ("ONE","TWO","THREE","FOUR", "FIVE")
reader = csv.DictReader( csvfile, fieldnames)
for row in reader:
    json.dump(row, jsonfile)
    jsonfile.write('\n')

fieldnames2 = ("DATE", "OPEN","HIGH","LOW","CLOSE")
reader = csv.DictReader( csvfile2, fieldnames2)
for row in reader:
    json.dump(row, jsonfile2)
    jsonfile2.write('\n')


fh = open("predictionf.json", "r")
fhnew = open("prediction.json", "wt")
fhnew.write("[")
prev_line = None
for line in fh:
	if prev_line is not None:
		fhnew.write(prev_line)
		fhnew.write(",")

	print(line)
	prev_line = line


fhnew.write(str(prev_line))
fhnew.write("]")

fh = open("dataf.json", "r")
fhnew = open("data.json", "wt")
fhnew.write("[")
prev_line = None
for line in fh:
	if prev_line is not None:
		fhnew.write(prev_line)
		fhnew.write(",")
	
	prev_line = line


fhnew.write(str(prev_line))
fhnew.write("]")

session1 = ftplib.FTP('203.124.114.1', 'almat', 'alma#007F')
file = open('/home/qawbecrdteyf/Desktop/St-matlab/prediction.json', 'rb')
#file2 = open('/home/qawbecrdteyf/Desktop/St-matlab/data.csv', 'rb')
session1.cwd('/chirag/')
session1.storbinary('STOR predictionf.json', file)
#session.storbinary('STOR data.csv', file2)
file.close()
#file2.close()
session1.quit()

session2 = ftplib.FTP('203.124.114.1', 'almat', 'alma#007F')
file = open('/home/qawbecrdteyf/Desktop/St-matlab/data.json', 'rb')
#file2 = open('/home/qawbecrdteyf/Desktop/St-matlab/data.csv', 'rb')
session2.cwd('/chirag/')
session2.storbinary('STOR dataf.json', file)
#session.storbinary('STOR data.csv', file2)
file.close()
#file2.close()
session2.quit()