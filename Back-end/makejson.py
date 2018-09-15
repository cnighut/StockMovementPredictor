fh = open("predictionf.json", "r")
fhnew = open("prediction.json", "wt")
fhnew.write("[")
prev_line = None
for line in fh:
	if prev_line is not None:
		fhnew.write(prev_line)
		fhnew.write(",")
	prev_line = line 


fhnew.write(prev_line)
fhnew.write("]")


fh2 = open("dataf.json", "r")
fh2new = open("data.json", "wt")
fh2new.write("[")
prev_line = None
for line in fh2:
	if prev_line is not None:
		fh2new.write(prev_line)
		fh2new.write(",")
	prev_line = line


fh2new.write(prev_line)
fh2new.write("]")
