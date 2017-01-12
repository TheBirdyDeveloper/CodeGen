require('luaLib/print')
function F2(X5)
   X5={ hd = nil, tl = X5 }

return X5
end
function F1(X2, X3)
   X1=X2
   X4=X3
   while X4 do
            X1=F2(X1)
      X4 = X4.tl --Décrémentation de la condition du For
   end

return X1
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

