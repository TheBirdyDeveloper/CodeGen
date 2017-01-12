require('luaLib/print')
function F1(X2)
   if X2 then 
      X1=nil
   else 
      X1={ hd = nil, tl = nil }
   end

return X1
end

local values = {F1(PARAMS)}

for k, v in pairs( values ) do
   print(k,printUniq(v))
end

