import pandas_datareader.data as pdr
import datetime
import csv
import pandas as pd
import numpy as np
import os.path
import os
import tensorflow as tf
from sklearn.linear_model import LogisticRegression
import matplotlib as plt
import quandl
quandl.ApiConfig.api_key = 'B9jw_Q_5vWGMHL3x7R9n'

try:
	os.remove("data.csv")
	print("file")
except OSError:
	pass


symbol = 'NSE/BHEL'
datetoday = datetime.datetime.today().strftime('%Y-%m-%d')
if (datetoday[8]=='2' and datetoday[9] == '9'):
	s = list(datetoday)
	s[9] = '8'
	datetoday = "".join(s)
remaindate = datetoday[4:100]
lastyear = str(int(datetoday[0:4])-2)
dateyearback = lastyear+remaindate 

def getandmodifydata(symbol, dateyearback, datetoday):
	df = pdr.DataReader(symbol, 'quandl', dateyearback, datetoday)
	df = df.iloc[::-1]
	#print(df.loc['2015-01-02'])
	#df = df.dropna()
	df = df.iloc[:,:5]
	#print(df)
	df['S_14'] = df['Close'].rolling(window=14).mean()

	#print(df['S_10'])
	#Should I go for 14?

	df['Corr'] = df['Close'].rolling(window=14).corr(df['S_14'])

	#print(df['Corr'])

	CHN = df['Close'] - df['Close'].shift(1)

	n = df['Close'].shape[0]
	Adva = [None] *n;
	Decl  =[None]*n
	#print(CHN)

	for index, i in enumerate(CHN):
		#print(i)
		if(i > 0):
			#print(i)
			#print('why')
			Adva[index]= i
			Decl[index] = 0

		else:
			#print(i)
			Adva[index] = 0
			Decl[index] = i*(-1)


	df['Adva']  =Adva

	df['Decl'] = Decl
	#print(df)

	df['AvgGain'] = df['Adva'].rolling(window=10).mean()
	df['AvgLoss'] = df['Decl'].rolling(window=10).mean()

	df['RS'] = df['AvgGain']/df['AvgLoss']

	df['RSI'] = 100 - ((100)/(1+df['RS']))

	df = df.iloc[:, [0,1,2,4,5,6,12]]
	df['Open-Close'] = df['Open'] - df['Close'].shift(1)
	df['Open-Open'] = df['Open'] - df['Open'].shift(1)
	df = df[np.isfinite(df['RSI'])]
	df = df[np.isfinite(df['Corr'])]
	y1 = np.where(df['Close'].shift(-1) > df['Close'],1,0)
	df['y_labels1'] = y1
	y2 = np.where(df['Close'].shift(-2) > df['Close'],1,0)
	df['y_labels2'] = y2
	y3 = np.where(df['Close'].shift(-3) > df['Close'],1,0)
	df['y_labels3'] = y3
	y4 = np.where(df['Close'].shift(-4) > df['Close'],1,0)
	df['y_labels4'] = y4
	y5 = np.where(df['Close'].shift(-5) > df['Close'],1,0)
	df['y_labels5'] = y5

	return df

def getdf2(df):
	df2 = df.tail(30)
	df3 = df2.tail(1)
	df3 = df3.iloc[:,[ 0,1,2,3]]
	with open('data.csv', 'a') as f:
		print("hell")
		df3.to_csv(f,header=False)
	return df2

def generatecsv(df, df2, symbol):
	outnew = "outnew"+symbol+".csv"
	outlaternew = "outlaternew"+symbol+".csv"
	with open(outnew, 'w') as f:
		df.to_csv(f,header=False)
	with open(outlaternew, 'w') as f:
		df2.to_csv(f,header=False)


def operate(symbol, name, dateyearback, datetoday):
	df1 = "df1"+name
	df2 = "df2" +name
	df1 = getandmodifydata(symbol, dateyearback, datetoday)
	df2 = getdf2(df1)
	df1[:-30]
	print(df1)
	print(df2)
	generatecsv(df1, df2, name)


operate('NSE/BHEL', 'BHEL', dateyearback,datetoday)

operate('NSE/SUNTV', 'SUNTV', dateyearback,datetoday)
operate('NSE/ICICIBANK', 'ICICI', dateyearback,datetoday)
operate('NSE/BHARATFORG', 'BF', dateyearback,datetoday)
operate('NSE/LTFH', 'LTFH', dateyearback,datetoday)
operate('NSE/RELIANCE', 'REL', dateyearback,datetoday)
operate('NSE/MARUTI', 'MARUTI', dateyearback,datetoday)
operate('NSE/INFY', 'INFY', dateyearback,datetoday)

operate('NSE/ASIANPAINT', 'ASP', dateyearback,datetoday)
operate('NSE/BPCL', 'BPCL', dateyearback,datetoday)
operate('NSE/BHARTIARTL', 'ARTL', dateyearback,datetoday)


operate('NSE/TATASTEEL', 'TS', dateyearback,datetoday)

operate('NSE/ITC', 'ITC', dateyearback,datetoday)
operate('NSE/LUPIN', 'LUPIN', dateyearback,datetoday)
operate('NSE/MCDOWELL_N', 'US', dateyearback,datetoday)


