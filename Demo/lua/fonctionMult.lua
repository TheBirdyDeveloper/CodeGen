require('luaLib/print')
function F3(X9)
   X9={ hd = nil, tl = X9 }

return X9
end
function F1(X2, X3)
   X1=nil
   X4=X3
   while X4 do
            X1=F2(X2, X1)
      X4 = X4.tl --Décrémentation de la condition du For
   end

return X1
end
function F2(X6, X7)
   X5=X6
   X8=X7
   while X8 do
            X5=F3(X5)
      X8 = X8.tl --Décrémentation de la condition du For
   end

return X5
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

