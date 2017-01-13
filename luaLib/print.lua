function printUniq(X)
if not X then return "nil" end
if type(X) == "string" then return X end
return "{ hd = "..printUniq(X.hd).." , tl = "..printUniq(X.tl).." }"
end 
