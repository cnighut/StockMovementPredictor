function predr = runcompany( symbol )
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
%%
out =strcat('outnew', symbol, '.csv');

data1= load(out);
X = data1(:, [2,3,4,5,6,7,8,9,10]); y = data1(:, [11,12,13,14,15]);
m = size(X, 1);

%can't visualise data. Seems huge
X = normalisefeatures(X);

%% creating network
input_layer_size  = size(X,2);  % 9 features
hidden_layer_size1 = 20;
hidden_layer_size2 = 20;
%hidden_layer_size3 = 25;% 25 hidden units
num_labels = 5;

%% Initialising theta

Theta1 = rand(hidden_layer_size1,input_layer_size+1);
Theta2 = rand(hidden_layer_size2,hidden_layer_size1+1);
Theta3 = rand(num_labels, hidden_layer_size2+1);
%Theta3 = rand(hidden_layer_size3, hidden_layer_size2+1);
%Theta4 = rand(num_labels, hidden_layer_size3+1);

nn_params = [Theta1(:) ; Theta2(:); Theta3(:)];

%% Feed forward

lambda = 0.2;
J = nnCostFunction(nn_params, input_layer_size, hidden_layer_size1, hidden_layer_size2, num_labels, X, y, lambda);

%% Initalise weights

initial_Theta1 = randInitializeWeights(input_layer_size, hidden_layer_size1);
initial_Theta2 = randInitializeWeights(hidden_layer_size1, hidden_layer_size2);
initial_Theta3 = randInitializeWeights(hidden_layer_size2, num_labels);
%initial_Theta3 = randInitializeWeights(hidden_layer_size2, hidden_layer_size3);
%initial_Theta4 = randInitializeWeights(hidden_layer_size3, num_labels);


% Unroll parameters
initial_nn_params = [initial_Theta1(:) ; initial_Theta2(:); initial_Theta3(:)];
 
%% Train

options = optimset('MaxIter', 5000);

%  You should also try different values of lambda
lambda = 0.007;

% Create "short hand" for the cost function to be minimized
costFunction = @(p) nnCostFunction(p, ...
                                   input_layer_size, ...
                                   hidden_layer_size1, hidden_layer_size2, ...
                                   num_labels, X, y, lambda);
% Now, costFunction is a function that takes in only one argument (the
% neural network parameters)
[nn_params, cost] = fmincg(costFunction, initial_nn_params, options);
%fprintf('%f', cost)
% Obtain Theta1 and Theta2 back from nn_params
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

%% =================Implement Predict =================
%  After training the neural network, we would like to use it to predict
%  the labels. You will now implement the "predict" function to use the
%  neural network to predict the labels of the training set. This lets
%  you compute the training set accuracy.

pred= predict(Theta1, Theta2, Theta3, X, num_labels);
for i = 1:num_labels
fprintf('\nTraining Set Accuracy: %f\n', mean(double(pred(:,i) == y(:,i))) * 100);
end

%% Future
outlater = strcat('outlaternew',symbol,'.csv');
data2= load(outlater);
Xlater = data2(:, [2,3,4,5,6,7,8,9,10]); ylater = data2(:,[11,12,13,14,15]);
normalisefeatures(X);
pred= predict(Theta1, Theta2, Theta3, Xlater, num_labels);
for i=1:num_labels
fprintf('\nTraining Set Accuracy of future of coloumn %d: %f\n', i,  mean(double(pred(:,i) == ylater(:,i))) * 100);
end
predr = pred(30, :);



end

