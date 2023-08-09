import { FC } from "react";

interface CommonNavbarProps {
  generation: string;
}

const CommonNavbar: FC<CommonNavbarProps> = ({ generation }) => {
  return <nav className="bg-black w-full h-full">{generation}</nav>;
};

export default CommonNavbar;
