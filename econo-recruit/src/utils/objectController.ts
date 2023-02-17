export const deepFreeze = (object: any) => {
  var propNames = Object.getOwnPropertyNames(object)
  for (let name of propNames) {
    let value = object[name]
    object[name] = value && typeof value === 'object' ? deepFreeze(value) : value
  }

  return Object.freeze(object)
}
