function p = predict(Theta1, Theta2, Theta3, X, num_labels)

m = size(X, 1);

p = zeros(size(X, 1), num_labels);

a1 = [ones(m, 1) X];
z2 = a1*Theta1';
a2 = [ones(size(z2, 1), 1) sigmoid(z2)];
z3 = a2*Theta2';
a3 = [ones(size(z3,1),1) sigmoid(z3)];
z4  = a3*Theta3';

h3 = sigmoid(z4);
%a4 = [ones(size(z4,1) ,1) sigmoid(z4)];
%z5 = a4*Theta4';
%h3 = sigmoid(z5);

num = 0;
num1 = 0;
for i=1:m
        if(h3(i,1) < 0.25)
            p(i,1) = 0;
            num = num + 1;
        else
            p(i,1) = 1;
            num1 = num1 +1;
        end
end
fprintf('\n num 0 is %d and num1 is %d \n', num, num1);
% =========================================================================
end
