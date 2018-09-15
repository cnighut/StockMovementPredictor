%logistic regression for our stocks of NSE
%Author - Chirag Nighut
%% Initialisation
clear;
close all;
clc;
fprintf('Started');
%% RUN Companies

pred1 = runcompany('BHEL');
dlmwrite('prediction.csv',pred1,'delimiter', ',');
pred2 = runcompany('SUNTV');
dlmwrite('prediction.csv',pred2,'-append');
pred3 = runcompany('ICICI');
dlmwrite('prediction.csv',pred3,'-append');
pred4 = runcompany('BF');
dlmwrite('prediction.csv',pred4,'-append');
pred5 = runcompany('LTFH');
dlmwrite('prediction.csv',pred5,'-append');
pred6 = runcompany('REL');
dlmwrite('prediction.csv',pred6,'-append');
pred7 = runcompany('MARUTI');
dlmwrite('prediction.csv',pred7,'-append');
pred8 = runcompany('INFY');
dlmwrite('prediction.csv',pred8,'-append');
pred9 = runcompany('ASP');
dlmwrite('prediction.csv',pred9,'-append');
pred10 = runcompany('BPCL');
dlmwrite('prediction.csv',pred10,'-append');
pred11 = runcompany('ARTL');
dlmwrite('prediction.csv',pred11,'-append');
pred12= runcompany('TS');
dlmwrite('prediction.csv',pred12,'-append');
pred13 = runcompany('ADP');
dlmwrite('prediction.csv',pred13,'-append');
pred14 = runcompany('LUPIN');
dlmwrite('prediction.csv',pred14,'-append');
pred15 = runcompany('US');
dlmwrite('prediction.csv',pred15,'-append');


%% ====================END===========================








