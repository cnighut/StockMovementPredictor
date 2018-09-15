

function [J, grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size1, hidden_layer_size2, ...
                                   num_labels, ...
                                  X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size1 * (input_layer_size + 1)), ...
                 hidden_layer_size1, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size1 * (input_layer_size + 1))):((hidden_layer_size1 * (input_layer_size + 1))+(hidden_layer_size2*(hidden_layer_size1 + 1)))), ...
                 hidden_layer_size2, hidden_layer_size1 + 1);

             
Theta3 = reshape(nn_params((1+((hidden_layer_size1 * (input_layer_size + 1))+(hidden_layer_size2*(hidden_layer_size1 + 1)))):end), ...
    num_labels, (hidden_layer_size2+1)); 
             
%Theta3 = reshape(nn_params((((hidden_layer_size1 * (input_layer_size + 1))+(hidden_layer_size2*(hidden_layer_size1 + 1)))+1): (((hidden_layer_size1 * (input_layer_size + 1))+(hidden_layer_size2*(hidden_layer_size1 + 1))+((hidden_layer_size3)*(hidden_layer_size2+1))))), ...
            %     hidden_layer_size3, (hidden_layer_size2+1));
             
%Theta4 = reshape(nn_params((1+(((hidden_layer_size1 * (input_layer_size + 1))+(hidden_layer_size2*(hidden_layer_size1 + 1))+((hidden_layer_size3)*(hidden_layer_size2+1))))): end), ...
                % num_labels, (hidden_layer_size3+1));


% Setup some useful variables
m = size(X, 1);
         
% You need to return the following variables correctly 
% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m

X = [ones(m,1) X];
a1 = X;
z2 = X*Theta1';
a2 = sigmoid(z2);
a2 = [ones(size(a2,1),1) a2];
z3 = a2*Theta2';
a3  = sigmoid(z3);
a3 = [ones(size(a3,1),1) a3];
z4 = a3*Theta3';
a4 = sigmoid(z4);
%a4 = [ones(size(a4,1), 1) a4];
%z5 = a4*Theta4';
%a5 = sigmoid(z5);
%changing y to Y. y is 5000x1 and Y is 5000x10
Y = y;
%for i=1:m
%  Y(i, :)= I(y(i), :);
%end

J = sum(sum(((-Y).*log(a4) - (1-Y).*log(1-a4)),1));
J = J/m;

p = sum(sum(abs(Theta1(:, 2:end)),2)) + sum(sum(abs(Theta2(:, 2:end)),2)) + sum(sum(abs(Theta3(:, 2:end)),2));
J = J + ((lambda/(2*m))*(p));

%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%

z2 = [ones(size(z2, 1), 1) z2];
z3 = [ones(size(z3, 1), 1) z3];
%z4 = [ones(size(z4, 1), 1) z4];
%smalldelta5 = a5-Y;
%smalldelta4 = (smalldelta5*Theta4).*(sigmoidGradient(z4));
%smalldelta4 = smalldelta4(:, 2:end);
smalldelta4  = a4-Y;
%smalldelta5 = a%z4 = [ones(size(z4, 1), 1) z4];
%smalldelta4 = (smalldelta5*Theta4).*(sigmoidGradient(z4));
%smalldelta4 = (smalldelta4(:, 2:end));
smalldelta3 = (smalldelta4*Theta3).*sigmoidGradient(z3);
smalldelta3 = smalldelta3(:, 2:end);
smalldelta2 = (smalldelta3*Theta2).*(sigmoidGradient(z2));
smalldelta2 = smalldelta2(:, 2:end);




smalldelta4 = a4-Y;



delta_1 = (smalldelta2'*a1)./m;
delta_2 = (smalldelta3'*a2)./m;
delta_3 = (smalldelta4'*a3)./m;
%delta_4 = (smalldelta5'*a4)./m;
p1 = (lambda/m)*[zeros(size(Theta1, 1), 1) Theta1(:, 2:end)];
p2 = (lambda/m)*[zeros(size(Theta2, 1), 1) Theta2(:, 2:end)];
p3 = (lambda/m)*[zeros(size(Theta3, 1), 1) Theta3(:, 2:end)];
%p4 = (lambda/m)*[zeros(size(Theta4, 1), 1) Theta4(:, 2:end)];
Theta1_grad = delta_1 + p1;
Theta2_grad = delta_2 + p2;
Theta3_grad = delta_3 + p3;
%Theta4_grad = delta_4 + p4;
% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:) ; Theta3_grad(:)];


end
