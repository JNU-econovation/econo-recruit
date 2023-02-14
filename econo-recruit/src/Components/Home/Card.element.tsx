type HomeCardElementType = {
  title: string
  subtitle: string
  href: string
}

const HomeCardElement = ({ title, subtitle, href }: HomeCardElementType) => {
  return (
    <a
      href={href}
      className="h-[45vh] w-[22vw] min-w-[20rem] min-h-[14rem] pt-16 p-12 border-l-[1px] border-t-[1px] flex flex-col justify-between bg-white hover:bg-black hover:text-white"
    >
      <div>
        <div className="font-bold text-2xl">{title}</div>
        <div className="text-lg">{subtitle}</div>
      </div>
      <div className="w-full flex flex-row-reverse">
        <img className="w-fit h-fit" src="/right.arrow.svg" alt="" />
      </div>
    </a>
  )
}

export default HomeCardElement
