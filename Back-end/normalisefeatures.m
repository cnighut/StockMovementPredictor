function [ X_norm ] = normalisefeatures( X )
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here

X_norm = X;
mu = mean(X);
sigma = max(X)-min(X);

for i=1:1:size(X,1)
    for j=1:1:size(X,2)
    %X(i,j) = (X(i, j)-mu(j))/sigma(j);
        X_norm(i,j) = (X(i, j)-mu(j))/sigma(j);

    end
end
    
end

