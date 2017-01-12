require('luaLib/print')
function F1(X1)
   X1={ hd = nil, tl = X1 }

return X1
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

