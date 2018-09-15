import csv
import json

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
    
  
